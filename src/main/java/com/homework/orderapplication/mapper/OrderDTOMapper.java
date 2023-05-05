package com.homework.orderapplication.mapper;

import com.homework.orderapplication.dto.CustomerDTO;
import com.homework.orderapplication.dto.OrderCustomerDTO;
import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderDTOMapper {
    private final ModelMapper modelMapper;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderDTOMapper(ModelMapper modelMapper, OrderItemMapper orderItemMapper) {
        this.modelMapper = modelMapper;
        this.orderItemMapper = orderItemMapper;
    }

    public OrderCustomerDTO mapOrderCustomerDTO(Order order) {
        CustomerDTO customerDTO = modelMapper.map(order.getCustomer(), CustomerDTO.class);
        return OrderCustomerDTO.builder()
                .customer(customerDTO)
                .status(order.getStatus())
                .items(orderItemMapper.mapOrderItemDTOs(order.getItems()))
                .build();
    }

    public Map<String, List<OrderDTO>> getRestaurantOrderMap(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(o -> o.getRestaurant().getName(),
                        Collectors.mapping(o -> modelMapper.map(o, OrderDTO.class), Collectors.toList())));
    }
}
