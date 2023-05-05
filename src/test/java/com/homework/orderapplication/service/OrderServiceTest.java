package com.homework.orderapplication.service;

import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.dto.OrderItemDTO;
import com.homework.orderapplication.mapper.OrderDTOMapper;
import com.homework.orderapplication.mapper.OrderMapper;
import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.repository.OrderRepository;
import com.homework.orderapplication.request.OrderCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderItemService orderItemService;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OrderDTOMapper orderDTOMapper;
    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository, orderItemService, orderMapper, orderDTOMapper, modelMapper);
    }

    @Test
    public void testCreateOrder() {
        List<OrderItemDTO> items = new ArrayList<>();
        items.add(OrderItemDTO.builder().orderId(1L).itemId(1L).quantity(1).build());
        items.add(OrderItemDTO.builder().orderId(2L).itemId(2L).quantity(3).specialInstructions("bla").build());

        OrderCreateRequest request = new OrderCreateRequest();
        request.setCustomer(1L);
        request.setRestaurant(1L);
        request.setOrderItems(items);

        Order order = new Order();
        when(orderMapper.map(request)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(new OrderDTO());

        OrderDTO result = orderService.createOrder(request);

        assertEquals(result.getClass(), OrderDTO.class);
        verify(orderMapper, times(1)).map(request);
        verify(orderRepository, times(1)).save(order);
        verify(orderItemService, times(1)).saveOrderItems(order, items);
        verify(modelMapper, times(1)).map(order, OrderDTO.class);
    }
}
