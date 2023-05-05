package com.homework.orderapplication.service;

import com.homework.orderapplication.dto.OrderCustomerDTO;
import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.dto.OrderItemDTO;
import com.homework.orderapplication.exception.CustomException;
import com.homework.orderapplication.mapper.OrderDTOMapper;
import com.homework.orderapplication.mapper.OrderMapper;
import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.model.Status;
import com.homework.orderapplication.repository.OrderRepository;
import com.homework.orderapplication.request.OrderCreateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final OrderMapper orderMapper;
    private final OrderDTOMapper orderDTOMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService, OrderMapper orderMapper, OrderDTOMapper orderDTOMapper, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.orderMapper = orderMapper;
        this.orderDTOMapper = orderDTOMapper;
        this.modelMapper = modelMapper;
    }

    public OrderDTO createOrder(OrderCreateRequest orderCreateRequest) {
        Order order = orderMapper.map(orderCreateRequest);
        Order savedOrder = saveOrder(order);
        List<OrderItemDTO> orderItemDTOS = orderCreateRequest.getOrderItems();
        orderItemService.saveOrderItems(savedOrder, orderItemDTOS);
        return modelMapper.map(savedOrder, OrderDTO.class);
    }

    public OrderCustomerDTO getOrderById(Long id) {
        Order order = getOrder(id);
        return orderDTOMapper.mapOrderCustomerDTO(order);
    }

    public Map<String, List<OrderDTO>> getOrdersByRestaurants() {
        List<Order> orders = orderRepository.findAll();
        return orderDTOMapper.getRestaurantOrderMap(orders);
    }

    public OrderDTO updateStatus(Long id, String status) {
        Order order = getOrder(id);
        order.setStatus(Status.valueOf(status.toUpperCase()));
        orderRepository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }

    private Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    private Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new CustomException("Order with id " + id + " not found", HttpStatus.NOT_FOUND));
    }
}
