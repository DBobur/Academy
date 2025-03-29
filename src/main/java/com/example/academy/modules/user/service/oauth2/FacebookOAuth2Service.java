package com.example.academy.modules.user.service.oauth2;

import com.example.academy.modules.user.enums.OAuth2Provider;
import com.example.academy.modules.user.enums.SuccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("facebook")
@RequiredArgsConstructor
public class FacebookOAuth2Service implements OAuth2Service {

    @Value("${oauth2.client.facebook.client-id}")
    private String clientId;

    @Value("${oauth2.client.facebook.client-secret}")
    private String clientSecret;

    @Value("${oauth2.client.facebook.redirect-url-login}")
    private String redirectUriLogin;

    @Value("${oauth2.client.facebook.redirect-url-register}")
    private String redirectUriRegister;

    private final RestTemplate restTemplate;

    @Override
    public OAuth2Provider getProvider() {
        return OAuth2Provider.FACEBOOK;
    }

    @Override
    public String getAuthorizationUrl(SuccessType type) {
        return "https://www.facebook.com/v17.0/dialog/oauth" +
                "?client_id=" + clientId +
                "&redirect_uri=" + ((type.equals(SuccessType.LOGIN)) ? redirectUriLogin : redirectUriRegister) +
                "&response_type=code" +
                "&scope=" + "public_profile";
    }

    @Override
    public String getAccessToken(String code, SuccessType type) {
        String url = "https://graph.facebook.com/v17.0/oauth/access_token";

        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", type.equals(SuccessType.LOGIN) ? redirectUriLogin : redirectUriRegister);
        params.put("grant_type", "authorization_code");

        ResponseEntity<Map> response = restTemplate.postForEntity(url, params, Map.class);
        return response.getBody().get("access_token").toString();
    }

    @Override
    public OAuthUserInfo getUserInfo(String accessToken) {
        String userInfoUri = UriComponentsBuilder
                .fromUriString("https://graph.facebook.com/me")
                .queryParam("fields", "id,name,email,picture")
                .queryParam("access_token", accessToken)
                .toUriString();

        Map<String, Object> userResponse = restTemplate.getForObject(userInfoUri, Map.class);

        return new OAuthUserInfo(
                (String) userResponse.get("id"),
                ((String) userResponse.get("name")).replaceAll("\\s+", "")+(String) userResponse.get("id"),
                (String) userResponse.get("name"),
                accessToken
        );
    }
}

