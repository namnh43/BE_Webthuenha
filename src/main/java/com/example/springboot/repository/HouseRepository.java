package com.example.springboot.repository;

import com.example.springboot.model.House;
import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    Iterable<House> findByUser(User user);
    @Query("SELECT h FROM House h JOIN FETCH h.images WHERE h.user.id = :userId")
    List<House> findByUserIdWithImages(@Param("userId") Long userId);
}
