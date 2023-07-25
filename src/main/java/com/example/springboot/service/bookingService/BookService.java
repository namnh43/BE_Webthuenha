package com.example.springboot.service.bookingService;

import com.example.springboot.exception.NotFoundException;
import com.example.springboot.exception.UnauthorizedException;
import com.example.springboot.model.*;
import com.example.springboot.repository.BookingRepository;
import com.example.springboot.repository.HouseRepository;
import com.example.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
                .bookingStatus(BookingStatus.BOOKING)
                .build();

        house.setHouseStatus(HouseStatus.IN_BOOKING);
        houseRepository.save(house);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public void cancelBookingForUser(Long bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);

        if (bookingOptional.isEmpty()) {
            throw new NotFoundException("Booking not found");
        }

        Booking booking = bookingOptional.get();
        User user = booking.getUser();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUserOptional = userRepository.findByUsername(username);

        if (currentUserOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User currentUser = currentUserOptional.get();
        if (!currentUser.equals(user)) {
            throw new UnauthorizedException("You are not authorized to cancel this booking");
        }

        Date currentDate = new Date(new java.util.Date().getTime());
        long diffInMillies = Math.abs(booking.getStartDate().getTime() - currentDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (diff <= 1) {
            throw new IllegalArgumentException("You can only cancel booking 1 day before the start date");
        }

        booking.setBookingStatus(BookingStatus.CANCELLED);
        booking.setUpdateAt(currentDate);
        bookingRepository.save(booking);
    }

}
