package com.homework.orderapplication.dto;

import lombok.*;

import java.util.List;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private Long id;
    private String name;
    private List<ItemDTO> menu;
}
