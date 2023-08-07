package com.example.springboot.controller.socketController;

import com.example.springboot.model.BookingNotify;
import com.example.springboot.model.House;
import com.example.springboot.service.house.HouseService;
import com.example.springboot.service.user.UserService;
import com.example.springboot.service.websocket.BookingNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Controller
@CrossOrigin("*")
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private BookingNotifyService bookingNotifyService;

    @MessageMapping("/notify")
    public BookingNotify NotifyBooking(BookingNotify notify) {
        System.out.println(notify.toString());
        return bookingNotifyService.save(notify);
    }
}
