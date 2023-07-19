package com.example.springboot.service;


import com.example.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends IGeneralService<User>{
    UserDetailsService userDetailsService();
    List<User> getAllHosts();
}