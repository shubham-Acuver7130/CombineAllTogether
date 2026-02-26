package com.example.OrderMicroService.OrderRepoPLSQL;

import com.example.OrderMicroService.OrderEntity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPLSql extends JpaRepository<OrderEntity,Integer> {
}
