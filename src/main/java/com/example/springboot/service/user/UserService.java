package com.example.springboot.service.user;


import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends IGeneralService<User> {
    UserDetailsService userDetailsService();
    List<User> getAllHosts();
    User acceptHost(Long id);

    User rejectHost(Long id);
    List<User> getUsersWithApplyHost();
    Long countHousesByUserId(Long userId);
}