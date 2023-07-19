package com.example.springboot.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
