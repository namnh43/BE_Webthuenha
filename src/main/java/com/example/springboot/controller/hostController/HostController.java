package com.example.springboot.controller.hostController;

import com.example.springboot.model.User;
import com.example.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/host")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HostController {
    @Autowired
    private UserService userService;
    @GetMapping("/list-host")
    public ResponseEntity<Iterable<User>> showListHost(){
        return ResponseEntity.ok(userService.getAllHosts());
    }

    @GetMapping("/{userId}/count")
    public Long countHousesByUserId(@PathVariable Long userId) {
        return userService.countHousesByUserId(userId);
    }
}
