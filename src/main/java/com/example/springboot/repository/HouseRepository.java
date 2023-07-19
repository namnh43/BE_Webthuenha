package com.example.springboot.repository;

import com.example.springboot.model.House;
import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HouseRepository extends JpaRepository<House,Long> {
     Iterable<House> findByUser(User user);
}
