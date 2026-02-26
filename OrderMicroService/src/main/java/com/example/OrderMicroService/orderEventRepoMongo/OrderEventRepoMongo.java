package com.example.OrderMicroService.orderEventRepoMongo;

import com.example.OrderMicroService.OrderEntity.OrderEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderEventRepoMongo extends MongoRepository<OrderEvent, String> {
}
