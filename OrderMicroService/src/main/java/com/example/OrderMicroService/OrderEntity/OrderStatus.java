package com.example.OrderMicroService.OrderEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

public enum OrderStatus {
    CREATED,
    SHIPPED,
    CANCELLED,
    DELIVERED
}