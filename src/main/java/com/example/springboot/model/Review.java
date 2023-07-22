package com.example.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double rating;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date(new java.util.Date().getTime());
    }
}
