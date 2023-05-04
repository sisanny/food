package com.homework.orderapplication.mapper;

import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.model.Status;
import com.homework.orderapplication.request.OrderCreateRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderMapper {
    public Order map(OrderCreateRequest orderCreateRequest) {
        return Order.builder()
                .customer(orderCreateRequest.getCustomer())
                .restaurant(orderCreateRequest.getRestaurant())
                .status(Status.RECEIVED)
                .items(new ArrayList<>())
                .build();
    }
}
