package com.example.springboot.service.review;

import com.example.springboot.model.Review;
import com.example.springboot.service.IGeneralService;

public interface IReviewService extends IGeneralService<Review> {

    void hideReview(Long id);
}
