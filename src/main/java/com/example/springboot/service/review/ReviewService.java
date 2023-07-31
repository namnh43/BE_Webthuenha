package com.example.springboot.service.review;

import com.example.springboot.exception.NotFoundException;
import com.example.springboot.exception.UnauthorizedException;
import com.example.springboot.model.Booking;
import com.example.springboot.model.Review;
import com.example.springboot.model.User;
import com.example.springboot.repository.BookingRepository;
import com.example.springboot.repository.ReviewRepository;
import com.example.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService{
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    public void createReviewFromBooking(Long id, Double rating, String content) {
        // Lấy đối tượng Booking từ cơ sở dữ liệu
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isEmpty()) {
            throw new NotFoundException("Booking not found with id " + id);
        }
        Booking booking = optionalBooking.get();

        // Kiểm tra xem người dùng viết đánh giá có phải là người dùng đã đặt phòng không
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUserOptional = userRepository.findByUsername(username);

        if (currentUserOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User currentUser =  currentUserOptional.get();
        if (!booking.getUser().equals(currentUser)) {
            throw new UnauthorizedException("You are not allowed to write a review for this booking");
        }

        // Tạo một đối tượng Review từ đối tượng Booking và thông tin đánh giá được cung cấp
        Review review = Review.builder()
                .user(currentUser)
                .rating(rating)
                .content(content)
                .house(booking.getHouse())
                .build();

        // Lưu đối tượng Review vào cơ sở dữ liệu
        reviewRepository.save(review);

        // Liên kết đối tượng Review với đối tượng Booking
        booking.setReview(review);
        bookingRepository.save(booking);
    }


    @Override
    public Iterable<Review> findAll() {
        return null;
    }

    @Override
    public Optional<Review> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Review save(Review review) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<Review> getReviewByHouseId(Long id) {
        return reviewRepository.findByHouseId(id);
    }
}