package com.ravindra.product_management.DTO;

import io.jsonwebtoken.security.Password;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String fullName;
    private String email;
    private String Password;
    private String Role;

}
