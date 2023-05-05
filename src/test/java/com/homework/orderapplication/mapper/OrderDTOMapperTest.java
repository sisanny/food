package com.homework.orderapplication.mapper;


import com.homework.orderapplication.dto.CustomerDTO;
import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.model.Order;
import com.homework.orderapplication.service.CustomerService;
import com.homework.orderapplication.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderDTOMapperTest {

    private OrderDTOMapper orderDTOMapper;

    @Mock
    private CustomerService customerService;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderDTOMapper = new OrderDTOMapper(customerService, restaurantService, modelMapper);
    }

    @Test
    public void mapOrderDTOWithCustomer_shouldMapOrderAndCustomerToOrderDTO() {
        Order order = Order.builder().id(1L).customer(1L).build();
        OrderDTO expectedOrderDTO = OrderDTO.builder()
                .customer(CustomerDTO.builder().id(1L).name("John").build())
                        .build();
        when(customerService.getCustomerDTO(1L)).thenReturn(expectedOrderDTO.getCustomer());
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(expectedOrderDTO);

        OrderDTO actualOrderDTO = orderDTOMapper.mapOrderDTOWithCustomer(order);

        assertEquals(expectedOrderDTO, actualOrderDTO);
    }

    @Test
    public void mapAllOrderDTO_shouldMapAllOrdersToOrderDTOList() {
        Order order1 = Order.builder().id(1L).restaurant(1L).build();
        Order order2 = Order.builder().id(2L).restaurant(2L).build();
        List<Order> orders = Arrays.asList(order1, order2);
        OrderDTO expectedOrderDTO1 = OrderDTO.builder().build();
        OrderDTO expectedOrderDTO2 = OrderDTO.builder().build();
        when(modelMapper.map(order1, OrderDTO.class)).thenReturn(expectedOrderDTO1);
        when(modelMapper.map(order2, OrderDTO.class)).thenReturn(expectedOrderDTO2);

        List<OrderDTO> actualOrderDTOList = orderDTOMapper.mapAllOrderDTO(orders);

        assertEquals(Arrays.asList(expectedOrderDTO1, expectedOrderDTO2), actualOrderDTOList);
    }

}
