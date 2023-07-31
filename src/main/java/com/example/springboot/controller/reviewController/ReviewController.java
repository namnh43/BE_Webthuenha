package com.example.springboot.controller.reviewController;

import com.example.springboot.model.Review;
import com.example.springboot.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/bookings/{id}")
    public ResponseEntity<Void> createReviewFromBooking(@PathVariable Long id,
                                                        @Param("rating") Double rating,
                                                        @Param("content") String content) {
        try {
            reviewService.createReviewFromBooking(id, rating, content);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/house/{id}")
    public ResponseEntity<List<Review>> getReviewByHouseId(@PathVariable Long id){
        List<Review> reviews = reviewService.getReviewByHouseId(id);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }
}