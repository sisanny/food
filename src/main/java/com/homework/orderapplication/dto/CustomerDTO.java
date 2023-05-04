package com.homework.orderapplication.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(value = "Name", example = "Anna")
    private String name;
    @ApiModelProperty(value = "Email", example = "anna@bla.com")
    private String email;
}
