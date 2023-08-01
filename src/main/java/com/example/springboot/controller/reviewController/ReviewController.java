package com.example.springboot.controller.reviewController;

import com.example.springboot.model.Review;
import com.example.springboot.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/bookings/{id}")
    public void createReviewFromBooking(@PathVariable Long id, @RequestBody Review reviewDto) {
        Integer rating = reviewDto.getRating();
        String content = reviewDto.getContent();
        reviewService.createReviewFromBooking(id, rating, content);
    }

    @PatchMapping("/{id}/hide")
    public ResponseEntity<String> hideReview(@PathVariable Long id) {
        reviewService.hideReview(id);
        return ResponseEntity.ok().build();
    }

}