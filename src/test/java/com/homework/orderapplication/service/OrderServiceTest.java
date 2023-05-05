package com.homework.orderapplication.service;

import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.dto.OrderItemDTO;
import com.homework.orderapplication.exception.CustomException;
import com.homework.orderapplication.mapper.OrderDTOMapper;
import com.homework.orderapplication.mapper.OrderMapper;
import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.model.Status;
import com.homework.orderapplication.repository.OrderRepository;
import com.homework.orderapplication.request.OrderCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    public void testUpdateStatusShouldUpdateOrderStatus() {
        Long id = 1L;
        String status = "PREPARING";
        Order order = Order.builder()
                .id(id)
                .customer(1L)
                .restaurant(1L)
                .status(Status.RECEIVED)
                .build();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(OrderDTO.builder().status(Status.PREPARING).build());

        OrderDTO result = orderService.updateStatus(id, status);

        verify(orderRepository, times(1)).findById(id);
        verify(orderRepository, times(1)).save(order);
        assertEquals(Status.PREPARING, order.getStatus());
        assertEquals(Status.PREPARING, result.getStatus());
    }

    @Test
    public void testUpdateStatusShouldThrowCustomExceptionWhenNotFound() {
        // Arrange
        Long id = 1L;
        String status = Status.PREPARING.name();
        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomException.class, () -> orderService.updateStatus(id, status));
        verify(orderRepository, times(1)).findById(id);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    public void testUpdateStatusShouldThrowExceptionWhenInvalidStatusProvided() {
        Long id = 1L;
        String status = "INVALID_STATUS";
        Order order = Order.builder()
                .id(id)
                .customer(1L)
                .restaurant(1L)
                .status(Status.RECEIVED)
                .build();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        assertThrows(IllegalArgumentException.class, () -> orderService.updateStatus(id, status));
        verify(orderRepository, times(1)).findById(id);
        verify(orderRepository, never()).save(any(Order.class));
    }
}
