package com.homework.orderapplication.dto;

import com.homework.orderapplication.model.Status;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCustomerDTO extends OrderDTO {
    private CustomerDTO customer;

    @Builder
    public OrderCustomerDTO(Status status, List<OrderItemDTO> items, CustomerDTO customer) {
        super(status, items);
        this.customer = customer;
    }
}
