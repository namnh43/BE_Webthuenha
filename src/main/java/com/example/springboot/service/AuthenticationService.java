package com.example.springboot.service;


import com.example.springboot.dto.request.SignInRequest;
import com.example.springboot.dto.request.SignUpRequest;
import com.example.springboot.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}