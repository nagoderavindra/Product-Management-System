package com.ravindra.product_management.Service;

import com.ravindra.product_management.DTO.AuthRequest;

public interface AuthService {
    String login(AuthRequest request);
}
