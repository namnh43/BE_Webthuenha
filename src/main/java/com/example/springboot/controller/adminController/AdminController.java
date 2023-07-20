package com.example.springboot.controller.adminController;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/list-user")
    public ResponseEntity<Iterable<User>> showAllListUser(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/list-host")
    public ResponseEntity<Iterable<User>> showListHost(){
        return ResponseEntity.ok(userService.getAllHosts());
    }

    @PostMapping("/accept-host/{id}")
    public ResponseEntity<User> acceptHost(@PathVariable Long id){
        User user =  userService.acceptHost(id);
        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/reject-host/{id}")
    public ResponseEntity<User> rejectHost(@PathVariable Long id){
        User user =  userService.rejectHost(id);
        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

}
