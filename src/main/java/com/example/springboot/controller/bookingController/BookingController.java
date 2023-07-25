package com.example.springboot.controller.bookingController;

import com.example.springboot.model.Booking;
import com.example.springboot.model.User;
import com.example.springboot.service.bookingService.IBookingService;
import com.example.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("booking")
@CrossOrigin("*")
public class BookingController {
    @Autowired
    private IBookingService bookingService;
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<Booking> create(@RequestBody Booking booking){
        System.out.println(booking);
        return new ResponseEntity<>(bookingService.save(booking),HttpStatus.OK);
    }

    //List booking by User


}
