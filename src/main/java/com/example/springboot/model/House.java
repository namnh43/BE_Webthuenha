package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private Integer totalBedrooms;
    private Integer totalBathrooms;
    private String address;
    private Integer price;
    private String description;
    private String featuredImage;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private Date createAt;
    private Double ratingScore;
    private Long numberOfRented;

    @Enumerated(EnumType.STRING)
    private HouseStatus houseStatus;

    @OneToMany(mappedBy = "house")
    private List<Image> images;
    @OneToMany(mappedBy = "house")
    private List<Review> reviews;

    @PrePersist
    public void setCreatedAt() {
        this.createAt = new Date(new java.util.Date().getTime());
        this.featuredImage = "https://a0.muscache.com/im/pictures/miso/Hosting-813137457313942137/original/34eaa638-9027-4e9a-9e91-239db6f2e844.jpeg?im_w=720";
    }
}
