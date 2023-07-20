package com.example.springboot.repository;

import com.example.springboot.model.House;
import com.example.springboot.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    Iterable<Image> findByHouse(House house);
    @Modifying
    @Query("DELETE FROM Image i WHERE i.house.id = :houseId")
    void deleteByHouseId(Long houseId);
}
