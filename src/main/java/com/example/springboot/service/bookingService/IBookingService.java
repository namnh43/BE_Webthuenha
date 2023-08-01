package com.example.springboot.service.bookingService;

import com.example.springboot.model.Booking;
import com.example.springboot.model.BookingDateRange;
import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IBookingService extends IGeneralService<Booking> {

    boolean createBooking(Booking booking);

    List<Booking> getBookingsByUser(User user);
    void cancelBookingForUser(Long bookingId);
    void checkInBooking(Long id);
    void checkOutBooking(Long id);

    List<Booking> findAllByOwner(User user);

    List<BookingDateRange> getBookingsByHouseId(Long houseId);
}
