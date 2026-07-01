package com.ravindra.product_management.Controller;

import com.ravindra.product_management.DTO.AuthRequest;
import com.ravindra.product_management.DTO.AuthResponse;
import com.ravindra.product_management.Service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;

    }
    @PostMapping("/login")
    public AuthResponse login (@RequestBody AuthRequest request)
    {
        String token= authService.login(request);
        return new AuthResponse(token);
    }
}