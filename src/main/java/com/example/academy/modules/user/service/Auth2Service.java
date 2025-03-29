package com.example.academy.modules.user.service;


import com.example.academy.core.config.security.JwtTokenUtil;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.enums.UserRole;
import com.example.academy.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class Auth2Service {

    //@Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId = "166259715248-2ogjekmtne4766oq2jdge0e624t2vkjg.apps.googleusercontent.com";

    //@Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret = "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";

    private String redirectUri = "http://localhost:8080/api/v1/auth/oauth2/code";

    private final RestTemplate restTemplate;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    // Google login link yasash
    public String generateGoogleOAuth2Link() {
        return "https://accounts.google.com/o/oauth2/v2/auth" +
                "?client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&response_type=code" +
                "&scope=openid%20email%20profile" +
                "&access_type=offline" +
                "&prompt=consent";
    }

    // Code orqali token va user info olish
    public String handleOAuth2Code(String code) {
        // 1. Code orqali access token olish
        Map<String, String> tokenResponse = exchangeCodeForAccessToken(code);
        String accessToken = tokenResponse.get("access_token");

        // 2. Access token orqali user info olish
        Map<String, Object> userInfo = fetchUserInfo(accessToken);
        String email = (String) userInfo.get("email");

        // 3. User bazadan olish yoki yaratish
        UserEntity user = userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(
                        UserEntity.builder()
                                .email(email)
                                .username(email) // email orqali unique bo'ladi
                                .password("OAUTH_USER") // default yoki random
                                .role(UserRole.USER) // default
                                .build()
                ));

        // 4. JWT token yaratish
        return jwtTokenUtil.generateToken(user.getEmail());
    }

    // Code dan access token olish
    private Map<String, String> exchangeCodeForAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                "https://oauth2.googleapis.com/token",
                HttpMethod.POST,
                request,
                Map.class
        );

        return response.getBody();
    }

    // User info olish
    private Map<String, Object> fetchUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo",
                HttpMethod.GET,
                request,
                Map.class
        );

        return response.getBody();
    }

    // User yaratish (agar mavjud bo'lmasa)
    private AppUser createUserFromOAuth2(Map<String, Object> userInfo) {
        AppUser user = new AppUser();
        user.setEmail((String) userInfo.get("email"));
        user.setName((String) userInfo.get("name"));
        return user;
    }
}

