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
        return null;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Booking save(Booking booking) {
        return null;
    }

    @Override
    public void remove(Long id) {

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

        house.setHouseStatus(HouseStatus.InBooking);
        houseRepository.save(house);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
