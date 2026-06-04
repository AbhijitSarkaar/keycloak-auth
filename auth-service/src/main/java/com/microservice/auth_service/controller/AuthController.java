package com.microservice.auth_service.controller;

import com.microservice.auth_service.payload.UserDTO;
import com.microservice.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/details")
    public ResponseEntity<List<?>> getUsers() {
        return new ResponseEntity<>(
                authService.getUsers(),
                HttpStatus.OK
        );
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDto) {
        return authService.register(userDto);
    }

}
