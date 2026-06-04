package com.microservice.auth_service.service;

import com.microservice.auth_service.payload.UserDTO;

public interface AuthService {

    String register(UserDTO userDto);

}


