package com.homework.orderapplication.repository;

import com.homework.orderapplication.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findById(Long id);

    List<OrderItem> findAll();
}
