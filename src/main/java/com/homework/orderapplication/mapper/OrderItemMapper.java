package com.homework.orderapplication.mapper;

import com.homework.orderapplication.dto.OrderItemDTO;
import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItem map(Order savedOrder, OrderItemDTO orderItemDTO) {
        return OrderItem.builder()
                .orderId(savedOrder)
                .itemId(orderItemDTO.getItemId())
                .quantity(orderItemDTO.getQuantity())
                .specialInstructions(orderItemDTO.getSpecialInstructions())
                .build();
    }
}
