package com.homework.orderapplication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private Long orderId;
    private Long itemId;
    private int quantity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "Special instructions", example = "no onion")
    private String specialInstructions;
}