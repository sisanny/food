package com.homework.orderapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.dto.OrderItemDTO;
import com.homework.orderapplication.exception.CustomError;
import com.homework.orderapplication.exception.CustomErrorMap;
import com.homework.orderapplication.request.OrderCreateRequest;
import com.homework.orderapplication.request.OrderUpdateRequest;
import com.homework.orderapplication.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    @Test
    public void testAddOrder() throws Exception {
        List<OrderItemDTO> items = new ArrayList<>();
        items.add(OrderItemDTO.builder().orderId(1L).itemId(1L).quantity(1).build());
        items.add(OrderItemDTO.builder().orderId(2L).itemId(2L).quantity(3).specialInstructions("bla").build());

        OrderCreateRequest request = new OrderCreateRequest();
        request.setCustomerId(1L);
        request.setRestaurantId(1L);
        request.setOrderItems(items);

        MvcResult result = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        OrderDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO.class);

        assertNotNull(response.getStatus());
        assertEquals(items.size(), response.getItems().size());
    }


    @Test
    public void testAddOrder_ShouldReturnWithCustomErrorMap() throws Exception {
        List<OrderItemDTO> items = new ArrayList<>();
        items.add(OrderItemDTO.builder().orderId(1L).itemId(1L).quantity(1).build());
        items.add(OrderItemDTO.builder().orderId(2L).itemId(2L).quantity(3).specialInstructions("bla").build());

        OrderCreateRequest request = new OrderCreateRequest();
        request.setOrderItems(items);

        MvcResult result = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals(400, response.getStatus());

        CustomErrorMap error = objectMapper.readValue(response.getContentAsString(), CustomErrorMap.class);
        assertEquals("Issues with the following: ", error.getProblem());

        Map<String, String> expectedFields = new HashMap<>();
        expectedFields.put("restaurantId", "You need to add the restaurant id!");
        expectedFields.put("customerId", "You need the customer id!");
        assertEquals(expectedFields, error.getFields());
    }

    @Test
    public void testAddOrder_ShouldReturnWithCustomErrorMap2() throws Exception {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setCustomerId(1L);
        request.setRestaurantId(1L);

        MvcResult result = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals(400, response.getStatus());

        CustomErrorMap error = objectMapper.readValue(response.getContentAsString(), CustomErrorMap.class);
        assertEquals("Issues with the following: ", error.getProblem());

        Map<String, String> expectedFields = new HashMap<>();
        expectedFields.put("orderItems", "You need to add at least one item!");
        assertEquals(expectedFields, error.getFields());
    }

    @Test
    public void testAddOrder_ShouldReturnWithCustomError() throws Exception {
        MvcResult result = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals(400, response.getStatus());

        CustomErrorMap error = objectMapper.readValue(response.getContentAsString(), CustomErrorMap.class);
        assertEquals("Issues with the following: ", error.getProblem());

        Map<String, String> expectedFields = new HashMap<>();
        expectedFields.put("orderCreateRequest", "must not be null");
        assertEquals(expectedFields, error.getFields());
    }

    @Test
    public void testGetOrderById() throws Exception {
        MvcResult result = mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
    }

    @Test
    public void testGetOrderByIdShouldReturnCustomErrorMap() throws Exception {
        MvcResult result = mockMvc.perform(get("/orders/-1"))
                .andExpect(status().isBadRequest())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals(400, response.getStatus());

        CustomErrorMap error = objectMapper.readValue(response.getContentAsString(), CustomErrorMap.class);
        assertEquals("Issues with the following: ", error.getProblem());

        Map<String, String> expectedFields = new HashMap<>();
        expectedFields.put("id", "must be greater than 0");
        assertEquals(expectedFields, error.getFields());
    }

    @Test
    public void testUpdateOrderStatus_InvalidEnumValue() throws Exception {
        Long orderId = 1L;
        String validEnumValue = "READY";

        OrderUpdateRequest request = new OrderUpdateRequest();
        request.setStatus(validEnumValue);

        MvcResult result = mockMvc.perform(patch("/orders/" + orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        OrderDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO.class);

        assertNotNull(response.getStatus());
        assertEquals(validEnumValue, response.getStatus().name());
    }

    @Test
    public void testUpdateOrderStatus_InvalidEnumValueShouldReturnCustomError() throws Exception {
        Long orderId = 1000L;
        String invalidEnumValue = "ready";

        OrderUpdateRequest request = new OrderUpdateRequest();
        request.setStatus(invalidEnumValue);

        MvcResult result = mockMvc.perform(patch("/orders/" + orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        CustomError error = objectMapper.readValue(response.getContentAsString(), CustomError.class);
        assertEquals("Order with id 1000 not found", error.getMessage());
    }

    @Test
    public void testUpdateOrderStatus_InvalidEnumValueShouldReturnCustomErrorMap() throws Exception {
        Long orderId = 1L;
        String invalidEnumValue = "INVALID_ENUM_VALUE";

        OrderUpdateRequest request = new OrderUpdateRequest();
        request.setStatus(invalidEnumValue);

        MvcResult result = mockMvc.perform(patch("/orders/" + orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        CustomErrorMap error = objectMapper.readValue(response.getContentAsString(), CustomErrorMap.class);
        assertEquals("Issues with the following: ", error.getProblem());

        Map<String, String> expectedFields = new HashMap<>();
        expectedFields.put("status", "Must be any of the following: received, preparing, ready or delivered");
        assertEquals(expectedFields, error.getFields());
    }
}

