package com.example.springboot.controller.houseController;

import com.example.springboot.model.House;
import com.example.springboot.model.HouseStatus;
import com.example.springboot.model.Image;
import com.example.springboot.model.User;
import com.example.springboot.service.house.IHouseService;
import com.example.springboot.service.img.IImageService;
import com.example.springboot.service.user.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create/{id}")
    public ResponseEntity<House> createHouse(@PathVariable long id, @RequestBody House house) {
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        House house1 = houseService.save(house);
        house1.setUser(optionalUser.get());
        Iterable<Image> images = house.getImages();
        for (Image image : images) {
            image.setHouse(house1);
            imageService.save(image);
        }
        return new ResponseEntity<>(houseService.save(house1), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable Long id) {
        houseService.remove(id);
    }

    @PutMapping("/{id}/update")
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
                                                        @Param("maxPrice") double maxPrice) {
        return new ResponseEntity<>(houseService.findBySearchCriteria(totalBedrooms, totalBathrooms, address, minPrice, maxPrice), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateHouseStatus(@PathVariable Long id, @RequestParam HouseStatus status) {
        houseService.updateHouseStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
