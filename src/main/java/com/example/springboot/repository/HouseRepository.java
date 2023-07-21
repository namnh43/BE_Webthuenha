package com.example.springboot.repository;

import com.example.springboot.model.House;
import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
     Iterable<House> findByUser(User user);
     @Query("SELECT COUNT(h) FROM House h WHERE h.user.id = :userId")
     Long countByUserId(@Param("userId") Long userId);
}
