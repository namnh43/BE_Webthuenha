package com.example.springboot.controller.houseController;

import com.example.springboot.dto.ApiResponse;
import com.example.springboot.model.House;
import com.example.springboot.model.HouseStatus;
import com.example.springboot.model.Image;
import com.example.springboot.model.User;
import com.example.springboot.service.house.IHouseService;
import com.example.springboot.service.img.IImageService;
import com.example.springboot.service.user.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/houses")
@CrossOrigin("*")
public class HouseController {
    @Autowired
    public IHouseService houseService;
    @Autowired
    public UserService userService;
    @Autowired
    public IImageService imageService;

    @GetMapping("/host/{id}")
    public ResponseEntity<Iterable<House>> getHousesByHostId(@PathVariable long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houseService.findByUser(optionalUser.get()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<House>> getHouses() {
        return new ResponseEntity<>(houseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getHouse(@PathVariable Long id) {
        Optional<House> optionalHouse = houseService.findById(id);
        if (!optionalHouse.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalHouse.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<House> createHouse(@RequestBody House house) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        house.setUser(user);
//        house = houseService.save(house);
//        Iterable<Image> images = house.getImages();
//        for (Image image : images) {
//            image.setHouse(house);
//            imageService.save(image);
//        }
        return new ResponseEntity<>(houseService.save(house), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteHouse(@PathVariable Long id) {
        houseService.remove(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateHouse(@PathVariable Long id, @Valid @RequestBody House house, BindingResult result) {
        try {
            if (result.hasErrors()) {
                System.out.println(result.getFieldErrors());
                StringBuilder msg = new StringBuilder();
                for (FieldError error:
                     result.getFieldErrors()) {
                    msg.append("[").append(error.getField()).append("] ").append(error.getDefaultMessage());
                }
                return new ResponseEntity<>(new ApiResponse("Dữ liệu đầu vào không đúng định dạng. Chi tiết: " + msg, "99", null), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse("success", "0", houseService.update(id, house)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), "99", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<House>> search(@Param("totalBedrooms") int totalBedrooms,
                                                        @Param("totalBathrooms") int totalBathrooms,
                                                        @Param("address") String address,
                                                        @Param("minPrice") double minPrice,
                                                        @Param("maxPrice") double maxPrice) {
        return new ResponseEntity<>(houseService.findBySearchCriteria(totalBedrooms, totalBathrooms, address, minPrice, maxPrice), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateHouseStatus(@PathVariable Long id, @RequestParam HouseStatus status) {
        return new ResponseEntity<>(houseService.updateHouseStatus(id, status), HttpStatus.OK);
    }
}
