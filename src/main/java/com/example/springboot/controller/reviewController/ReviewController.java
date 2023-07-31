package com.example.springboot.controller.reviewController;

import com.example.springboot.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/bookings/{id}")
    public void createReviewFromBooking(@PathVariable Long id,
                                        @Param("rating") Double rating,
                                        @Param("content") String content) {
        reviewService.createReviewFromBooking(id, rating, content) ;
    }
}