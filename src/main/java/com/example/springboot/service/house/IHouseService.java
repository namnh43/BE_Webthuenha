package com.example.springboot.service.house;

import com.example.springboot.model.House;
import com.example.springboot.service.IGeneralService;

import java.util.List;

public interface IHouseService extends IGeneralService<House> {
//     Iterable<House> findByUser(User user);
    List<House> getHousesByUserId(Long userId);
}
