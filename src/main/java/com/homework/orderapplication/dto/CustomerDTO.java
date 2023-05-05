package com.homework.orderapplication.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
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
