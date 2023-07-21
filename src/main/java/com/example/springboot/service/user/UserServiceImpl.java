package com.example.springboot.service.user;

import com.example.springboot.dto.request.ChangePasswordRequest;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;

import com.example.springboot.service.mail.EmailService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllHosts() {
        return userRepository.findAllByRole(Role.HOST);
    }

    public ResponseEntity<String> applyHost(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();
        user.setApplyHost(true);
        userRepository.save(user);

        return ResponseEntity.ok("Apply host successfully");
    }

    @Override
    public User acceptHost(Long id, String message) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getRole().equals(Role.ADMIN)) return null;
            user.setRole(Role.HOST);
            user.setApplyHost(false);
            userRepository.save(user);
            String to = user.getEmail();
            String subject = "Accept Host";
            emailService.sendSimpleEmail(to, subject, message);
            return user;
        }
        return null;
    }

    @Override
    public User rejectHost(Long id, String message) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setApplyHost(false);
            userRepository.save(user);
            String to = user.getEmail();
            String subject = "Reject Host";
            emailService.sendSimpleEmail(to, subject, message);
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
}