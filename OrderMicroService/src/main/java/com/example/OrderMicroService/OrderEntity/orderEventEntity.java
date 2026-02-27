package com.example.OrderMicroService.OrderEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orderEvents")
@Data
@Getter @Setter
public class orderEventEntity {
    @Id
    private String eventId;
    private Integer orderId;
    private String eventType;
    private String status;
    private String timestamp;
}
