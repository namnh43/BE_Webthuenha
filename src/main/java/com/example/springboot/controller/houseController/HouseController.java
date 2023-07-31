package com.example.springboot.controller.houseController;

import com.example.springboot.exception.NotFoundException;
import com.example.springboot.model.House;
import com.example.springboot.model.Image;
import com.example.springboot.model.User;
import com.example.springboot.service.house.IHouseService;
import com.example.springboot.service.img.IImageService;
import com.example.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/house")
@CrossOrigin("*")
public class HouseController {
    @Autowired
    public IHouseService houseService;
    @Autowired
    public UserService userService;
    @Autowired
    public IImageService imageService;

    @GetMapping("/host/{id}")
    public ResponseEntity<Iterable<House>> listHouseByUser(@PathVariable long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houseService.findByUser(optionalUser.get()), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<House>> listHouse() {
        return new ResponseEntity<>(houseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> findById(@PathVariable Long id) {
        Optional<House> optionalHouse = houseService.findById(id);
        if (!optionalHouse.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalHouse.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<House> createHouse(@RequestBody House house) {
        var user = userService.getCurrentUser();
        House house1 = houseService.save(house);
        house1.setUser(user);
        Iterable<Image> images = house.getImages();
        for (Image image : images) {
            image.setHouse(house1);
            imageService.save(image);
        }
        return new ResponseEntity<>(houseService.save(house1), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        Optional<House> optionalHouse = houseService.findById(id);
        if (!optionalHouse.isPresent()) {
            throw new NotFoundException("House not found");
        }
        imageService.deleteAllByHouse(optionalHouse.get());
        houseService.remove(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<House> update(@PathVariable Long id, @RequestBody House house) {
        Optional<House> optionalHouse = houseService.findById(id);
        if (!optionalHouse.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        imageService.deleteAllByHouse(optionalHouse.get());
        house.setId(id);
        House house1 = houseService.save(house);
        Iterable<Image> images = house.getImages();
        for (Image image : images) {
            image.setHouse(house1);
            imageService.save(image);
        }
        return new ResponseEntity<>(houseService.save(house1), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<House>> findBySearch(@Param("totalBedrooms") int totalBedrooms,
                                                        @Param("totalBathrooms") int totalBathrooms,
                                                        @Param("address") String address,
                                                        @Param("minPrice") double minPrice,
                                                        @Param("maxPrice") double maxPrice,
                                                        @Param("startDate") Date startDate,
                                                        @Param("endDate") Date endDate) {
        return new ResponseEntity<>(houseService.findBySearchCriteriaAndTimeRange(totalBedrooms, totalBathrooms, address, minPrice, maxPrice,startDate,endDate), HttpStatus.OK);
    }

}
