package com.example.springboot.controller.socketController;

import com.example.springboot.model.Booking;
import com.example.springboot.model.BookingNotify;
import com.example.springboot.model.House;
import com.example.springboot.model.User;
import com.example.springboot.service.bookingService.BookService;
import com.example.springboot.service.house.HouseService;
import com.example.springboot.service.user.UserService;
import com.example.springboot.service.websocket.BookingNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookingNotifyService bookingNotifyService;

    @MessageMapping("/notify")
    public BookingNotify NotifyBooking(BookingNotify notify) {
        System.out.println("Go to websocket message" + notify.toString());
        //Get booking_id
        Long booking_id = notify.getBooking().getId();
        Optional<Booking> booking = bookService.findById(booking_id);
        if (booking.isPresent()) {
            System.out.println("go here");
            //Get user & host
            User requestUser = booking.get().getUser();
            notify.setSourceUser(requestUser);
            House house = booking.get().getHouse();
            //Get user from house object
            User host = house.getUser();
            notify.setTargetUser(host);
            System.out.println("send to user id "+host.getId().toString());

            simpMessagingTemplate.convertAndSendToUser(host.getId().toString(),"/booking",notify);
        }
        return bookingNotifyService.save(notify);
    }
    @GetMapping("/notify_booking")
    public ResponseEntity<List<BookingNotify>> getAllNotify() {
        System.out.println("Go to get mapping request");
        return new ResponseEntity<>((List<BookingNotify>)bookingNotifyService.findAll(), HttpStatus.OK);
    }
}
