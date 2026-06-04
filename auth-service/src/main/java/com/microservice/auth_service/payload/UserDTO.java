package com.microservice.auth_service.payload;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
}
