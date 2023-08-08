package com.example.springboot.service.house;

import com.example.springboot.exception.NotFoundException;
import com.example.springboot.model.BookingStatus;
import com.example.springboot.model.House;
import com.example.springboot.model.User;
import com.example.springboot.repository.HouseRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service

public class HouseService implements IHouseService {
    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserRepository userRepository;

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
    public List<House> findBySearchCriteriaAndTimeRange(int totalBedrooms,
                                            int totalBathrooms,
                                            String address,
                                            double minPrice,
                                            double maxPrice,
                                            Date startDate,
                                            Date endDate) {
        return houseRepository.findBySearchCriteriaAndTimeRange(totalBedrooms,totalBathrooms,address,minPrice,maxPrice,startDate,endDate);
    }

    @Transactional
    @Override
    public House blockHouse(Long houseId) {
        houseRepository.blockHouse(houseId);
        return houseRepository.findById(houseId).get();
    }

    @Transactional
    @Override
    public House UnBlockHouse(Long houseId) {
        houseRepository.UnBlockHouse(houseId);
        return houseRepository.findById(houseId).get();
    }

    @Override
    public List<House> findAllByOrderByIdDesc() {
        return houseRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<House> getRelatedHouses(Long userId, Long houseId) {
        return houseRepository.findRelatedHouses(userId, houseId);
    }
}
