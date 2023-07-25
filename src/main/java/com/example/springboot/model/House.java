package com.example.springboot.model;

import com.example.springboot.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "houses")

public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Min(1)
    @Max(99)
    private Integer totalBedrooms;
    @Min(1)
    @Max(99)
    private Integer totalBathrooms;
    private String address;
    private Integer price;
    private String description;
    private String featuredImage;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @JsonFormat(pattern = Constants.DATETIME_FORMAT)
    private Date createdAt;
    private Double ratingScore;
    private Long numberOfRented;

    @Enumerated(EnumType.STRING)
    private HouseStatus houseStatus;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images;
    @OneToMany
    private List<Review> reviews;


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date(new java.util.Date().getTime());
    }
}
