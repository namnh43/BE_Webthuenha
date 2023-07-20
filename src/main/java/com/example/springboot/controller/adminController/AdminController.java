package com.example.springboot.controller.adminController;

import com.example.springboot.model.User;
import com.example.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/apply-host")
    public ResponseEntity<List<User>> showListApplyHost(){
        return new ResponseEntity<>(userService.getUsersWithApplyHost(), HttpStatus.OK);
    }
    @PostMapping("/accept-host/{id}")
    public ResponseEntity<User> acceptHost(@PathVariable Long id){
        if (userService.acceptHost(id) != null) {
            return ResponseEntity.ok(userService.acceptHost(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/reject-host/{id}")
    public ResponseEntity<User> rejectHost(@PathVariable Long id){
        if (userService.rejectHost(id) != null) {
            return ResponseEntity.ok(userService.rejectHost(id));
        }
        return ResponseEntity.notFound().build();
    }

}
