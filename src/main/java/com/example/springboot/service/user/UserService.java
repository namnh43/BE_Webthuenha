package com.example.springboot.service.user;


import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends IGeneralService<User> {
    UserDetailsService userDetailsService();
    List<User> getAllHosts();
    User updateRoleToHost(String username);
    User acceptHost(Long id);

    User rejectHost(Long id);
}