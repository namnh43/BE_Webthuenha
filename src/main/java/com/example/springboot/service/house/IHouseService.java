package com.example.springboot.service.house;

import com.example.springboot.model.House;
import com.example.springboot.model.HouseStatus;
import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHouseService extends IGeneralService<House> {

     Iterable<House> findByUser(User user);
     Long countHouseByUserId(Long userId);
     List<House> findBySearchCriteria(@Param("totalBedrooms") int totalBedrooms, @Param("totalBathrooms") int totalBathrooms,
                                      @Param("address") String address,
                                      @Param("minPrice") double minPrice,
                                      @Param("maxPrice") double maxPrice);
     House updateHouseStatus(Long id, HouseStatus status);

     House update(Long id, House user);

}
