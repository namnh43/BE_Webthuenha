package com.example.springboot.service.house;

import com.example.springboot.model.House;
import com.example.springboot.model.HouseStatus;
import com.example.springboot.model.Image;
import com.example.springboot.model.User;
import com.example.springboot.repository.HouseRepository;
import com.example.springboot.service.img.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class HouseService implements IHouseService {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private ImageService imageService;

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
    public Iterable<House> findByUser(User user) {
        return houseRepository.findByUser(user);
    }


    @Override
    public Long countHouseByUserId(Long userId) {
        return houseRepository.countByUserId(userId);
    }

    @Override
    public List<House> findBySearchCriteria(int totalBedrooms, int totalBathrooms, String address, double minPrice, double maxPrice) {
        return houseRepository.findBySearchCriteria(totalBedrooms, totalBathrooms, address, minPrice, maxPrice);
    }

    @Override
    public House updateHouseStatus(Long id, HouseStatus status) {
        House house = houseRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        house.setHouseStatus(status);
        return houseRepository.save(house);
    }

    @Override
    public House update(Long id, House request) {
        if (id != request.getId()) {
            throw new RuntimeException("ID không chính xác");
        }
        if (request.getTotalBedrooms() < 1 || request.getTotalBedrooms() > 99) {
            throw new RuntimeException("Số phòng ngủ không đúng");
        }
        if (request.getTotalBathrooms() > 99 || request.getTotalBathrooms() > 99) {
            throw new RuntimeException("Số phòng tắm không đúng");
        }
        House house = findById(id).orElseThrow((() -> new RuntimeException("Not found")));
        house.setName(request.getName());
        house.setTotalBathrooms(request.getTotalBathrooms());
        house.setTotalBathrooms(request.getTotalBathrooms());
        house.setAddress(request.getAddress());
        house.setPrice(request.getPrice());
        house.setDescription(request.getDescription());
        house.setFeaturedImage(request.getFeaturedImage());
//        imageService.deleteAllByHouse(house);
//        House house1 = houseService.save(house);
//        Iterable<Image> images = house.getImages();
//        for (Image image : images) {
//            image.setHouse(house1);
//            imageService.save(image);
//        }
        return houseRepository.save(house);
    }

}
