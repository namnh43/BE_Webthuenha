package com.example.springboot.repository;

import com.example.springboot.model.House;
import com.example.springboot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    Iterable<House> findByUser(User user);
    @Query("SELECT h, i.fileUrl FROM House h LEFT JOIN Image i ON i.house.id = h.id WHERE h.user.id = ?1")
    List<House> findByUserIdWithImages(Long userId);
    //     Page<House> findAll(Pageable pageable);

}
