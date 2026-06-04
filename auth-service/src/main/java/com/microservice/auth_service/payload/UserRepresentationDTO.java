package com.microservice.auth_service.payload;

import lombok.Data;

@Data
public class UserRepresentationDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
