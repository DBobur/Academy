package com.example.academy.modules.user.service.oauth2;

import com.example.academy.modules.user.enums.OAuth2Provider;
import com.example.academy.modules.user.enums.SuccessType;


public interface OAuth2Service {
    OAuth2Provider getProvider();
    String getAuthorizationUrl(SuccessType type);
    String getAccessToken(String code, SuccessType type);
    OAuthUserInfo getUserInfo(String accessToken);
}
