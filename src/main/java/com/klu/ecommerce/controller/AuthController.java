package com.klu.ecommerce.controller;

import com.klu.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")  // Base path for authentication
@CrossOrigin("*")         // Allow CORS from all origins
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Signup endpoint: POST /back1/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");

        // Call UserService to register the user
        return ResponseEntity.ok(userService.registerUser(username, email, password));
    }

    // Login endpoint: POST /back1/auth/login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // Call UserService to login the user
        return ResponseEntity.ok(userService.loginUser(username, password));
    }
}
