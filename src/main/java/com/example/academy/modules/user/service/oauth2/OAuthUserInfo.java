package com.example.academy.modules.user.service.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OAuthUserInfo {
    private String id;
    private String email;
    private String name;
    private String provider;
}
