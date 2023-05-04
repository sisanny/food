package com.homework.orderapplication.controller;

import com.homework.orderapplication.dto.OrderDTO;
import com.homework.orderapplication.exception.CustomError;
import com.homework.orderapplication.request.OrderCreateRequest;
import com.homework.orderapplication.request.OrderUpdateRequest;
import com.homework.orderapplication.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;
@Validated
@RestController
@RequestMapping(path = "/orders")
@Api(value = "Order", tags = {"Order"})
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the order"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Issue occurred with saving the entity", response = CustomError.class)
    })
    @PostMapping()
    public OrderDTO addOrder(@Valid @RequestBody OrderCreateRequest orderCreateRequest) {
        return orderService.createOrder(orderCreateRequest);
    }

    @ApiOperation(value = "Get all orders")
    @ApiResponse(code = 200, message = "Successfully retrieved the orders")
    @GetMapping()
    public ResponseEntity<Map<String, List<OrderDTO>>> getAllOrders() {
        Map<String, List<OrderDTO>> orders = orderService.getOrdersByRestaurants();
        return ResponseEntity.ok(orders);
    }

    @ApiOperation(value = "Get an order by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the order"),
            @ApiResponse(code = 404, message = "Order not found", response = CustomError.class)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") @Positive Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @ApiOperation(value = "Update an order's status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the order status"),
            @ApiResponse(code = 404, message = "Order not found", response = CustomError.class)
    })
    @PatchMapping(value = "/{id}")
    public OrderDTO updateOrderStatus(@PathVariable("id") @Positive Long id, @Valid @RequestBody OrderUpdateRequest orderUpdateRequest) {
        return orderService.updateStatus(id, orderUpdateRequest.getStatus());
    }
}
