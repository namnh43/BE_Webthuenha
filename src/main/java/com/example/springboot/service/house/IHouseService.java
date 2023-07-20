package com.example.springboot.service.house;

import com.example.springboot.model.House;
import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;

import java.util.List;

public interface IHouseService extends IGeneralService<House> {
     Iterable<House> getHouseByUser(User user);
     List<Object[]> findHousesByUserIdWithImages(Long userId);
}
