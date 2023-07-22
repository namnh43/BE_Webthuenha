package com.example.springboot.repository;

import com.example.springboot.model.House;
import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
     Iterable<House> findByUser(User user);
     @Query("SELECT COUNT(h) FROM House h WHERE h.user.id = :userId")
     Long countByUserId(@Param("userId") Long userId);
     @Query(value = "SELECT * FROM houses WHERE " +
             "CASE WHEN :totalBedrooms = 0 THEN true ELSE total_bedrooms = :totalBedrooms END = true " +
             "AND CASE WHEN :totalBathrooms = 0 THEN true ELSE total_bathrooms = :totalBathrooms END = true  " +
             "AND address LIKE %:address% " +
             "AND price >= :minPrice AND price <= :maxPrice", nativeQuery = true)
     List<House> findBySearchCriteria(@Param("totalBedrooms") Integer totalBedrooms,
                                      @Param("totalBathrooms") Integer totalBathrooms,
                                      @Param("address") String address,
                                      @Param("minPrice") Double minPrice,
                                      @Param("maxPrice") Double maxPrice);
}
