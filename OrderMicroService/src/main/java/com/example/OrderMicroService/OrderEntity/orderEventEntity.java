package com.example.OrderMicroService.OrderEntity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orderEvents")
@Data
public class orderEventEntity {
    @Id
    private String eventId;
    private Integer orderId;
    private String eventType;
    private String status;
    private String timestamp;
}
