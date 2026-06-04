package com.microservice.auth_service.service;

import com.microservice.auth_service.payload.UserDTO;

import java.util.List;

public interface AuthService {

    String register(UserDTO userDto);

    List getUsers();
}




