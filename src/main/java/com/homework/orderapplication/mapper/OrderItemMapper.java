package com.homework.orderapplication.mapper;

import com.homework.orderapplication.dto.OrderItemDTO;
import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.model.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public OrderItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderItem map(Order savedOrder, OrderItemDTO orderItemDTO) {
        return OrderItem.builder()
                .orderId(savedOrder)
                .itemId(orderItemDTO.getItemId())
                .quantity(orderItemDTO.getQuantity())
                .specialInstructions(orderItemDTO.getSpecialInstructions())
                .build();
    }

    public List<OrderItemDTO> mapOrderItemDTOs(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDTO.class))
                .collect(Collectors.toList());
    }
}
