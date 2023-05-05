package com.homework.orderapplication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private Long orderId;
    private Long itemId;
    @ApiModelProperty(value = "How many items", example = "2")
    private int quantity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "Special instructions", example = "no onion")
    private String specialInstructions;
}