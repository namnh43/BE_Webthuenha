package com.example.springboot.controller.houseController;

import com.example.springboot.model.House;

import com.example.springboot.model.User;
import com.example.springboot.service.house.IHouseService;

import com.example.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/house")
@CrossOrigin("*")
public class HouseController {
    @Autowired
    public IHouseService houseService;
    @Autowired
    public UserService userService;

    @GetMapping("find/{id}")
    public ResponseEntity<Iterable<House>> listHouseByUser(@PathVariable long id){
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houseService.findByUser(optionalUser.get()),HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<Page<House>> listHouse( @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(houseService.findAll(pageable), HttpStatus.OK);
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

    @GetMapping("user/{userId}")
    public List<House> getHousesWithImagesByUserId(@PathVariable Long userId) {
        return houseService.getHousesWithImagesByUserId(userId);
    }

}
