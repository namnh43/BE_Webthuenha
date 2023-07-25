package com.example.springboot.service.bookingService;

import com.example.springboot.model.*;
import com.example.springboot.repository.BookingRepository;
import com.example.springboot.repository.HouseRepository;
import com.example.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookingService{
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void remove(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking createBooking(Long userId, Long houseId, Date startDate, Date endDate, Integer price, Integer total) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        House house = houseRepository.findById(houseId).orElseThrow(() -> new RuntimeException("House not found"));

        Booking booking = Booking.builder()
                .user(user)
                .house(house)
                .startDate(startDate)
                .endDate(endDate)
                .price(price)
                .total(total)
                .bookingStatus(BookingStatus.WAITING_FOR_CHECKIN)
                .build();

        house.setHouseStatus(HouseStatus.IN_BOOKING);
        houseRepository.save(house);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }
}
