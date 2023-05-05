package com.homework.orderapplication.mapper;


import com.homework.orderapplication.dto.CustomerDTO;
import com.homework.orderapplication.dto.OrderCustomerDTO;
import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.model.Customer;
import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderDTOMapperTest {

    private OrderDTOMapper orderDTOMapper;

    @Mock
    private OrderItemMapper orderItemMapper;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderDTOMapper = new OrderDTOMapper(modelMapper, orderItemMapper);
    }

    @Test
    public void mapOrderDTOWithCustomer_shouldMapOrderAndCustomerToOrderDTO() {
        Order order = Order.builder().id(1L).customer(Customer.builder().id(1L).build()).build();
        CustomerDTO customerDTO = CustomerDTO.builder().id(1L).name("John").build();
        when(modelMapper.map(order.getCustomer(), CustomerDTO.class)).thenReturn(customerDTO);

        OrderCustomerDTO actualOrderDTO = orderDTOMapper.mapOrderCustomerDTO(order);

        assertEquals(customerDTO.getId(), actualOrderDTO.getCustomer().getId());
    }
}

