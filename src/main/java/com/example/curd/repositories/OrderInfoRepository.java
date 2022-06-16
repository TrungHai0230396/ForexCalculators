package com.example.curd.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.curd.models.OrderInfo;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
}
