package com.example.springboot.repository;

import com.example.springboot.model.Booking;
import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Iterable<Booking> findByUser(User user);
}
