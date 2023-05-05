package com.homework.orderapplication.request;

import com.homework.orderapplication.dto.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest {
    @NotNull(message = "You need the customer id!")
    private Long customerId;
    @NotNull(message = "You need to add the restaurant id!")
    private Long restaurantId;
    @NotEmpty(message = "You need to add at least one item!")
    private List<OrderItemDTO> orderItems;
}
