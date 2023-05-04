package com.homework.orderapplication.service;

import com.homework.orderapplication.dto.OrderItemDTO;
import com.homework.orderapplication.mapper.OrderItemMapper;
import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.model.OrderItem;
import com.homework.orderapplication.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemService {
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemMapper orderItemMapper, OrderItemRepository orderItemRepository) {
        this.orderItemMapper = orderItemMapper;
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrderItems(Order savedOrder, List<OrderItemDTO> orderItemDTOS) {
        for (OrderItemDTO orderItemDTO : orderItemDTOS) {
            OrderItem orderItem = orderItemMapper.map(savedOrder, orderItemDTO);
            savedOrder.getItems().add(orderItem);
            orderItemRepository.save(orderItem);
        }
    }
}
