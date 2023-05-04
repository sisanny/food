package com.homework.orderapplication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.homework.orderapplication.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CustomerDTO customer;
    private Long restaurant;
    private Status status;
    private List<OrderItemDTO> items;
}
