package com.example.OrderMicroService.orderEventRepoMongo;

import com.example.OrderMicroService.OrderEntity.OrderEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderEventRepoMongo extends MongoRepository<OrderEvent, String> {
    Optional<OrderEvent> findTopByOrderIdOrderByTimestampDesc(Integer orderId);
}
