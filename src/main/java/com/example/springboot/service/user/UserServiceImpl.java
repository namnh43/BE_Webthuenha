package com.example.springboot.service.user;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;

import com.example.springboot.service.house.IHouseService;
import com.example.springboot.service.mail.EmailService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private IHouseService houseService;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllHosts() {
        return userRepository.findAllByRole(Role.HOST);
    }

    @Override
    public User acceptHost(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getRole().equals(Role.ADMIN)) return null;
            user.setRole(Role.HOST);
            user.setApplyHost(false);
            userRepository.save(user);
            String to = user.getEmail();
            String subject = "Accept Host";
            String text = "Welcome to HomeLand. You are now a host";
            emailService.sendSimpleEmail(to, subject, text);
            return user;
        }
        return null;
    }

    @Override
    public User rejectHost(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setApplyHost(false);
            userRepository.save(user);
            String to = user.getEmail();
            String subject = "Reject Host";
            String text = "Sorry. You are not qualified to be a host!";
            emailService.sendSimpleEmail(to, subject, text);
            return user;
        }
        return null;
    }

    @Override
    public List<User> getUsersWithApplyHost() {
        return userRepository.findByApplyHost(true);
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> getHostUsersWithHouseCount() {
        List<User> hostUserList = userRepository.findHostUsers(Role.HOST);

        List<Map<String, Object>> result = new ArrayList<>();
        for (User hostUser : hostUserList) {
            Long houseCount = houseService.countHouseByUserId(hostUser.getId());

            Map<String, Object> hostUserWithHouseCount = new HashMap<>();
            hostUserWithHouseCount.put("user", hostUser);
            hostUserWithHouseCount.put("houseCount", houseCount);

            result.add(hostUserWithHouseCount);
        }

        return result;
    }
}