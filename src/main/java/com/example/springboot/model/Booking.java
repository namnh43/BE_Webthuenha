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
@Table(name = "reservations")

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "house_id", referencedColumnName = "id")
    private House house;
    private Date startDate;
    private Date endDate;
    private Integer price;
    private Integer total;
    private Date createAt;
    private Date updateAt;
    @OneToOne
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review review;

    @Enumerated(EnumType.STRING)
    private StatusReservation statusReservation;

    @PrePersist
    public void setCreatedAt() {
        this.createAt = new Date(new java.util.Date().getTime());
    }
}
