package com.homework.orderapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Long restaurantId;
}

