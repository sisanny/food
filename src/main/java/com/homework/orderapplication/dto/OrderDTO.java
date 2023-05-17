package com.homework.orderapplication.dto;

import com.homework.orderapplication.model.Status;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Status status;
    private List<OrderItemDTO> items;
}
