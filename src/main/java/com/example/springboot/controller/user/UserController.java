package com.example.springboot.controller.user;


import com.example.springboot.dto.request.ChangePasswordRequest;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;

import com.example.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<User> detailUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/apply-host")
    public ResponseEntity<String> applyHost() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.applyHost(username);
    }

    //@Bean PasswordEncoder sẽ bị gọi thành vòng lặp trong userService nên viết luôn ở controller
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        User user = userOptional.get();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid current password");

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("New password must be different from the current password");

        String newPasswordEncoded = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(newPasswordEncoded);
        userRepository.save(user);

        return ResponseEntity.ok("Password changed successfully");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateHostById(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateHostById(id, user), HttpStatus.OK);
    }
}
