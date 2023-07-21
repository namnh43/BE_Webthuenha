package com.example.springboot.service.user;


import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends IGeneralService<User> {
    UserDetailsService userDetailsService();

    List<User> getAllHosts();

    User acceptHost(Long id);

    User rejectHost(Long id);

    User blockUser(Long id);

    List<User> getUsersWithApplyHost();

    List<Map<String, Object>> getHostUsersWithHouseCount();

    User getHostById(Long id);

    User updateHostById(Long id, User user);

    User unlockUser(Long id);
}