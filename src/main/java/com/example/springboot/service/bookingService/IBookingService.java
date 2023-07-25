package com.example.springboot.service.bookingService;

import com.example.springboot.model.Booking;
import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IBookingService extends IGeneralService<Booking> {

    Booking createBooking(Long userId, Long houseId, Date startDate, Date endDate, Integer price, Integer total);

    List<Booking> getBookingsByUser(User user);
}
