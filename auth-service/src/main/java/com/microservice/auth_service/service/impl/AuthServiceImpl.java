package com.microservice.auth_service.service.impl;

import com.microservice.auth_service.payload.UserDTO;
import com.microservice.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public String register(UserDTO userDto) {

        return "registered";
    }

    @Override
    public List getUsers() {
        try {
            var authRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId("keycloak-client")
                    .principal("machine")
                    .build();

            var client = manager.authorize(authRequest);
            String token = client.getAccessToken().getTokenValue();

            System.out.println("token: " + token);

            List users = restClient.get()
                    .uri("http://localhost:8123/admin/realms/microservices/users")
                    .header("Authorization",
                            "Bearer " + token)
                    .retrieve()
                    .body(List.class);

            return users;

        } catch(RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
