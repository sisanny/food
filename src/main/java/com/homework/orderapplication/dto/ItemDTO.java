package com.homework.orderapplication.dto;

import lombok.*;

@Setter
@Getter
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

