package com.microservice.auth_service.service;

import com.microservice.auth_service.payload.UserRepresentationDTO;

import java.util.List;

public interface AuthService {

    String register(UserRepresentationDTO userDto);

    List getUsers();
}




