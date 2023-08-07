package com.example.springboot.service.websocket;

import com.example.springboot.model.Booking;
import com.example.springboot.model.BookingNotify;
import com.example.springboot.repository.BookingNotifyRepository;
import com.example.springboot.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingNotifyService implements IBookingNotifyService{

    @Autowired
    private BookingNotifyRepository bookingNotifyRepository;

    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Iterable<BookingNotify> findAll() {
        return bookingNotifyRepository.findAll();
    }

    @Override
    public Optional<BookingNotify> findById(Long id) {
        return bookingNotifyRepository.findById(id);
    }

    @Override
    public BookingNotify save(BookingNotify notify) {
        return bookingNotifyRepository.save(notify);
    }

    @Override
    public void remove(Long id) {
        bookingNotifyRepository.deleteById(id);
    }

    @Override
    public BookingNotify createBookingNotify(BookingNotify notify) {
        //Get booking_id
        Long booking_id = notify.getBooking().getId();
        return null;
    }

    @Override
    public List<BookingNotify> getBookingNotifyByBooking(Booking booking) {
        return null;
    }
}
