package com.example.academy.modules.user.controller;

import com.example.academy.core.config.security.JwtTokenUtil;
import com.example.academy.modules.user.enums.OAuth2Provider;
import com.example.academy.modules.user.enums.SuccessType;
import com.example.academy.modules.user.service.oauth2.OAuth2Service;
import com.example.academy.modules.user.service.oauth2.OAuth2ServiceFactory;
import com.example.academy.modules.user.service.oauth2.OAuthUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/oauth2")
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2ServiceFactory oAuth2ServiceFactory;
    private final JwtTokenUtil jwtTokenService;


    @GetMapping("/login/{provider}")
    public ResponseEntity<String> getAuthUrlForLogin(@PathVariable("provider") String providerName) {
        OAuth2Provider provider = OAuth2Provider.valueOf(providerName.toUpperCase());
        String url = oAuth2ServiceFactory.getService(provider).getAuthorizationUrl(SuccessType.LOGIN);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/register/{provider}")
    public ResponseEntity<String> getAuthUrlForRegister(@PathVariable("provider") String providerName) {
        OAuth2Provider provider = OAuth2Provider.valueOf(providerName.toUpperCase());
        String url = oAuth2ServiceFactory.getService(provider).getAuthorizationUrl(SuccessType.REGISTER);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/callback-login/{provider}")
    public ResponseEntity<String> callbackLogin(@PathVariable("provider") String providerName,
                                           @RequestParam("code") String code) {
        OAuth2Provider provider = OAuth2Provider.valueOf(providerName.toUpperCase());
        OAuth2Service service = oAuth2ServiceFactory.getService(provider);

        String accessToken = service.getAccessToken(code, SuccessType.LOGIN);
        OAuthUserInfo userInfo = service.getUserInfo(accessToken);

        String jwt = jwtTokenService.generateToken(userInfo.getEmail());
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/callback-register/{provider}")
    public ResponseEntity<String> callbackRepository(
            @PathVariable("provider") String providerName,
            @RequestParam("code") String code) {
        OAuth2Provider provider = OAuth2Provider.valueOf(providerName.toUpperCase());
        OAuth2Service service = oAuth2ServiceFactory.getService(provider);

        String accessToken = service.getAccessToken(code, SuccessType.REGISTER);
        OAuthUserInfo userInfo = service.getUserInfo(accessToken);

        String jwt = jwtTokenService.generateToken(userInfo.getEmail());
        return ResponseEntity.ok(jwt);
    }
}

