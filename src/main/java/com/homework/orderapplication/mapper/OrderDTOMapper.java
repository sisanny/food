package com.homework.orderapplication.mapper;

import com.homework.orderapplication.dto.CustomerDTO;
import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.service.CustomerService;
import com.homework.orderapplication.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderDTOMapper {
    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderDTOMapper(CustomerService customerService, RestaurantService restaurantService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.restaurantService = restaurantService;
        this.modelMapper = modelMapper;
    }

    public OrderDTO mapOrderDTOWithCustomer(Order order) {
        CustomerDTO customerDTO = customerService.getCustomerDTO(order.getCustomer());
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setCustomer(customerDTO);
        return orderDTO;
    }

    public List<OrderDTO> mapAllOrderDTO(List<Order> orders) {
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public Map<String, List<OrderDTO>> mapRestaurantOrders(List<OrderDTO> ordersList) {
        Map<String, List<OrderDTO>> restaurantOrdersMap = new HashMap<>();
        for (OrderDTO orderDTO : ordersList) {
            String restaurantName = getRestaurantName(orderDTO.getRestaurant());
            List<OrderDTO> restaurantOrders = restaurantOrdersMap.computeIfAbsent(restaurantName, k -> new ArrayList<>());
            restaurantOrders.add(orderDTO);
        }
        return restaurantOrdersMap;
    }

    private String getRestaurantName(Long restaurantId) {
        return restaurantService.mapRestaurantDTOById(restaurantId).getName();
    }
}
