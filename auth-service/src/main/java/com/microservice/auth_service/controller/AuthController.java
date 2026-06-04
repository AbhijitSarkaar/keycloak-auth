package com.microservice.auth_service.controller;

import com.microservice.auth_service.payload.UserDTO;
import com.microservice.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDto) {
        return authService.register(userDto);
    }

}
