package com.example.OrderMicroService.OrderEntity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "Orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String productId;
    private Double price;
    private LocalDateTime createdAt;


    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
