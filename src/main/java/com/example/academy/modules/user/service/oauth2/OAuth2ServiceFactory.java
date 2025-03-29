package com.example.academy.modules.user.service.oauth2;

import com.example.academy.modules.user.enums.OAuth2Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OAuth2ServiceFactory {

    private final Map<OAuth2Provider, OAuth2Service> serviceMap;

    @Autowired
    public OAuth2ServiceFactory(List<OAuth2Service> services) {
        serviceMap = new HashMap<>();
        for (OAuth2Service service : services) {
            serviceMap.put(service.getProvider(), service);
        }
    }


    public OAuth2Service getService(OAuth2Provider provider) {
        OAuth2Service service = serviceMap.get(provider);
        if (service == null) {
            throw new IllegalArgumentException("OAuth2 provider not supported: " + provider);
        }
        return service;
    }
}
