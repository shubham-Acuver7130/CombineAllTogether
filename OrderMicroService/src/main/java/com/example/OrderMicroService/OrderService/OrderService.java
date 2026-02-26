package com.example.OrderMicroService.OrderService;

import com.example.OrderMicroService.OrderEntity.OrderEntity;
import com.example.OrderMicroService.OrderEntity.OrderEvent;
import com.example.OrderMicroService.OrderEntity.OrderStatus;
import com.example.OrderMicroService.OrderRepoPLSQL.OrderPLSql;
import com.example.OrderMicroService.orderEventRepoMongo.OrderEventRepoMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private  OrderPLSql orderPLSql;
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    @Autowired
    private OrderEventRepoMongo orderEventRepoMongo;


    public void saveOrders(OrderEntity orders) {
        OrderEntity saveOrders=orderPLSql.save(orders);
        publishEvent(saveOrders, String.valueOf(OrderStatus.CREATED));
    }

    public void shipOrder(Integer orderId) {

        OrderEntity order = orderPLSql.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
//        OrderEntity updated = orderPLSql.save(order);

        publishEvent(order, String.valueOf(OrderStatus.SHIPPED));
    }

    public void cancelOrder(Integer orderId) {

        OrderEntity order = orderPLSql.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

//        order.setStatus(OrderStatus.CANCELLED);
//        OrderEntity updated = orderPLSql.save(order);

        publishEvent(order, String.valueOf(OrderStatus.CANCELLED));
    }

    private void publishEvent(OrderEntity order, String eventType) {

        OrderEvent event = OrderEvent.newBuilder()
                .setEventId(UUID.randomUUID().toString())
                .setOrderId(order.getOrderId())
                .setEventType(eventType)
                .setStatus(eventType)
                .setTimestamp(Instant.now().toString())
                .build();

        // 1️⃣ Send to Kafka


        // 2️⃣ Save to MongoDB
        OrderEvent mongoEvent = new OrderEvent();
        mongoEvent.setEventId(event.getEventId());
        mongoEvent.setOrderId(event.getOrderId());
        mongoEvent.setEventType(event.getEventType());
        mongoEvent.setStatus(event.getStatus());
        mongoEvent.setTimestamp(event.getTimestamp());

        orderEventRepoMongo.save(mongoEvent);
        System.out.println("saved in mongo");
        kafkaTemplate.send("order-topic", event);
    }

}
