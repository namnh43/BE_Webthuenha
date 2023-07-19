package com.example.springboot.controller.security;

import com.example.springboot.dao.request.SignInRequest;
import com.example.springboot.dao.request.SignUpRequest;
import com.example.springboot.dao.response.JwtAuthenticationResponse;
import com.example.springboot.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @GetMapping("/testToken")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World!");
    }
}