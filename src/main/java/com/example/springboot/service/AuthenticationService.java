package com.example.springboot.service;


import com.example.springboot.dao.request.SignInRequest;
import com.example.springboot.dao.request.SignUpRequest;
import com.example.springboot.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}