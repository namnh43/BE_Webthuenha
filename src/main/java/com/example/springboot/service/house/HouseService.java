package com.example.springboot.service.house;

import com.example.springboot.model.House;
import com.example.springboot.model.Image;
import com.example.springboot.model.User;
import com.example.springboot.repository.HouseRepository;
import com.example.springboot.repository.ImageRepository;
import com.example.springboot.service.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class HouseService implements IHouseService {
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
    public Iterable<House> getHouseByUser(User user) {
        return null;
    }

    //    @Override
//    public Iterable<House> findByUser(User user) {
//        return houseRepository.findByUser(user);
    @Override
    public List<Object[]> findHousesByUserIdWithImages(Long userId) {
        return houseRepository.findByUserIdWithImages(userId);
    }


//    public Page<House> getUsers(int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return houseRepository.findAll(pageable);
//    }
}
