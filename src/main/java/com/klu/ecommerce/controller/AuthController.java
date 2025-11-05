package com.klu.ecommerce.controller;

import com.klu.ecommerce.model.User;
import com.klu.ecommerce.repository.UserRepository;
import com.klu.ecommerce.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/back1/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> signup(@RequestBody Map<String,String> userMap) {
        User user = new User();
        user.setUsername(userMap.get("username"));
        user.setEmail(userMap.get("email"));
        user.setPassword(passwordEncoder.encode(userMap.get("password")));
        userRepository.save(user);

        Map<String,String> response = new HashMap<>();
        response.put("message", "User registered successfully!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,String> userMap) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userMap.get("username"), userMap.get("password"))
        );

        String token = jwtUtil.generateToken(userMap.get("username"));
        Map<String,String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
