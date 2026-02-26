package com.example.OrderMicroService.OrderController;

import com.example.OrderMicroService.OrderEntity.OrderEntity;
import com.example.OrderMicroService.OrderService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/orders")
    public ResponseEntity<String> acceptOrder(@RequestBody OrderEntity orders) {
        try {
            orderService.saveOrders(orders) ;
            return ResponseEntity.ok("Messaged saved");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping("/orders/{id}/ship")
    public ResponseEntity<String> shipOrder(@PathVariable Integer id) {
        try {
            orderService.shipOrder(id); ;
            return ResponseEntity.ok("Messaged saved");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping("/orders/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Integer id) {
        try {
            orderService.cancelOrder(id); ;
            return ResponseEntity.ok("Messaged saved");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
