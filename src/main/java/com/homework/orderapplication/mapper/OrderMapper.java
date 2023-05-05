package com.homework.orderapplication.mapper;

import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.model.Status;
import com.homework.orderapplication.request.OrderCreateRequest;
import com.homework.orderapplication.service.CustomerService;
import com.homework.orderapplication.service.RestaurantService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderMapper {

    private final CustomerService customerService;
    private final RestaurantService restaurantService;

    public OrderMapper(CustomerService customerService, RestaurantService restaurantService) {
        this.customerService = customerService;
        this.restaurantService = restaurantService;
    }

    public Order map(OrderCreateRequest orderCreateRequest) {
        return Order.builder()
                .customer(customerService.getCustomer(orderCreateRequest.getCustomerId()))
                .restaurant(restaurantService.getRestaurantById(orderCreateRequest.getRestaurantId()))
                .status(Status.RECEIVED)
                .items(new ArrayList<>())
                .build();
    }
}
