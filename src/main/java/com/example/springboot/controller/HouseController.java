package com.example.springboot.controller;

import com.example.springboot.model.House;
import com.example.springboot.service.house.IHouseService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<House>> DetailHouse(@PathVariable Long id){
//        Optional<House> house = houseService.findById(id);
//        if (house != null) {
//            return new ResponseEntity<>(house, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

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

    @PostMapping("/api")
    public ResponseEntity<House> createHouse(@RequestBody House house) {
        return new ResponseEntity<>(houseService.save(house), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        houseService.remove(id);
    }

    @PutMapping("/api/{id}")
    public ResponseEntity<House> update(@PathVariable Long id, @RequestBody House house) {
        Optional<House> optionalHouse = houseService.findById(id);
        if (!optionalHouse.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        house.setId(id);
        return new ResponseEntity<>(houseService.save(house), HttpStatus.OK);
    }
}
