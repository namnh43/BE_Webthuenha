package com.example.springboot.service.house;

import com.example.springboot.model.House;
import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IHouseService extends IGeneralService<House> {
     Iterable<House> findByUser(User user);
     Page<House> findAll(Pageable pageable);
}
