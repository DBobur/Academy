package com.example.academy.modules.user.service.oauth2;

import com.example.academy.modules.user.enums.OAuth2Provider;
import com.example.academy.modules.user.enums.SuccessType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service("google")
@RequiredArgsConstructor
@Slf4j
@Profile("dev")
public class GoogleOAuth2Service implements OAuth2Service {
    @Value("${oauth2.client.google.client-id}")
    private String clientId;

    @Value("${oauth2.client.google.client-secret}")
    private String clientSecret;

    @Value("${oauth2.client.google.redirect-url-login}")
    private String redirectUriLogin;

    @Value("${oauth2.client.google.redirect-url-register}")
    private String redirectUriRegister;

    private final RestTemplate restTemplate;

    @Override
    public OAuth2Provider getProvider() {
        return OAuth2Provider.GOOGLE;
    }

    @Override
    public String getAuthorizationUrl(SuccessType type) {
        return "https://accounts.google.com/o/oauth2/v2/auth" +
                "?client_id=" + clientId +
                "&redirect_uri=" + (type.equals(SuccessType.LOGIN)?redirectUriLogin:redirectUriRegister) +
                "&response_type=code" +
                "&scope=openid%20email%20profile" +
                "&access_type=offline" +
                "&prompt=consent";
    }

    @Override
    public String getAccessToken(String code, SuccessType type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", (type.equals(SuccessType.LOGIN)?redirectUriLogin:redirectUriRegister));
        body.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                "https://oauth2.googleapis.com/token",
                HttpMethod.POST,
                request,
                Map.class
        );

        Map<String,String> body1 = response.getBody();
        return body1.get("access_token");
    }

    @Override
    public OAuthUserInfo getUserInfo(String accessToken) {
        String url = "https://www.googleapis.com/oauth2/v3/userinfo";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        Map<String, Object> body = response.getBody();

        OAuthUserInfo userInfo = new OAuthUserInfo();
        userInfo.setEmail(body.get("email").toString());
        userInfo.setName(body.get("name").toString());
        userInfo.setProvider(getProvider().name());
        return userInfo;
    }
}