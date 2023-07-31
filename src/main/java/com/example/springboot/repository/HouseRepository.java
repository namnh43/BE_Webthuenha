package com.example.springboot.repository;

import com.example.springboot.model.BookingStatus;
import com.example.springboot.model.House;
import com.example.springboot.model.HouseStatus;
import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
     Iterable<House> findByUser(User user);

     @Query("SELECT COUNT(h) FROM House h WHERE h.user.id = :userId")
     Long countByUserId(@Param("userId") Long userId);

     @Query(value = "SELECT * FROM houses h WHERE " +
             "CASE WHEN :totalBedrooms = 0 THEN true ELSE h.total_bedrooms = :totalBedrooms END = true " +
             "AND CASE WHEN :totalBathrooms = 0 THEN true ELSE h.total_bathrooms = :totalBathrooms END = true " +
             "AND h.address LIKE %:address% " +
             "AND h.price >= :minPrice AND h.price <= :maxPrice " +
             "AND NOT EXISTS (SELECT * FROM bookings b WHERE b.house_id = h.id AND ((:startDate BETWEEN b.start_date AND b.end_date) OR (:endDate BETWEEN b.start_date AND b.end_date) OR (b.start_date BETWEEN :startDate AND :endDate)))",
             nativeQuery = true)
     List<House> findBySearchCriteriaAndTimeRange(@Param("totalBedrooms") Integer totalBedrooms,
                                                  @Param("totalBathrooms") Integer totalBathrooms,
                                                  @Param("address") String address,
                                                  @Param("minPrice") Double minPrice,
                                                  @Param("maxPrice") Double maxPrice,
                                                  @Param("startDate") Date startDate,
                                                  @Param("endDate") Date endDate);
     Optional<House> findByName(String name);

     @Query("SELECT h FROM House h WHERE h.user = :user AND h.name LIKE CONCAT('%',:name,'%') AND h.houseStatus = :houseStatus")
     List<House> findHousesByUserAndNameAndStatus(User user, String name, HouseStatus houseStatus);


}
