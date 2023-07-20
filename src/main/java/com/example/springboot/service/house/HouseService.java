package com.example.springboot.service.house;

import com.example.springboot.model.House;
import com.example.springboot.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class HouseService implements IHouseService{
    @Autowired
    private HouseRepository houseRepository;
    @Override
    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public void remove(Long id) {
        houseRepository.deleteById(id);
    }

    @Override
    public List<House> getHousesByUserId(Long userId) {
        return houseRepository.findByUserId(userId);
    }

//    @Override
//    public Iterable<House> findByUser(User user) {
//        return houseRepository.findByUser(user);
//    }
}
