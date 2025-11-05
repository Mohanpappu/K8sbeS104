package com.klu.ecommerce.controller;

import com.klu.ecommerce.model.User;
import com.klu.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/back1/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> signup(@RequestBody Map<String,String> userMap) {
        String message = userService.registerUser(userMap.get("username"), userMap.get("email"), userMap.get("password"));
        return ResponseEntity.ok(Map.of("message", message));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,String> userMap) {
        String token = userService.loginUser(userMap.get("username"), userMap.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }
}
