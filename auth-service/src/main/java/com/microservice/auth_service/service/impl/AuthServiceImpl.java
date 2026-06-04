package com.microservice.auth_service.service.impl;

import com.microservice.auth_service.payload.UserDTO;
import com.microservice.auth_service.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String register(UserDTO userDto) {
        return "registered";
    }
}
