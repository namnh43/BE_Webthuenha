package com.example.springboot.controller.adminController;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Iterable<User>> showAllListUser(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/list-host")
    public ResponseEntity<Iterable<User>> showListHost(){
        return ResponseEntity.ok(userService.getAllHosts());
    }
}
