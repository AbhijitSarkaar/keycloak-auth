package com.microservice.auth_service.service.impl;

import com.microservice.auth_service.payload.UserRepresentationDTO;
import com.microservice.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    RestClient restClient;

    @Autowired
    OAuth2AuthorizedClientManager manager;

    @Value("${keycloak.service.url}")
    String keyCloakUrl;

    @Override
    public String register(UserRepresentationDTO userDto) {
        ResponseEntity<Void> response = restClient.post()
                .uri(keyCloakUrl + "/admin/realms/microservices/users")
                .header("Authorization",
                        "Bearer " + getAccessToken())
                .contentType(MediaType.APPLICATION_JSON)
                .body(userDto)
                .retrieve()
                .toBodilessEntity();
        return "registered";
    }

    @Override
    public List getUsers() {
        try {
            List users = restClient.get()
                    .uri(keyCloakUrl + "/admin/realms/microservices/users")
                    .header("Authorization",
                            "Bearer " + getAccessToken())
                    .retrieve()
                    .body(List.class);
            return users;
        } catch(RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    String getAccessToken() {
        var authRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("keycloak-client")
                .principal("machine")
                .build();
        var client = manager.authorize(authRequest);
        return client.getAccessToken().getTokenValue();
    }

}
