package com.example.springboot.repository;

import com.example.springboot.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface HouseRepository extends JpaRepository<House, Long> {
    //     Iterable<House> findByUser(User user);
    @Query("SELECT h FROM House h WHERE h.user_id.id = :userId")
    List<House> findByUserId(Long userId);
}
