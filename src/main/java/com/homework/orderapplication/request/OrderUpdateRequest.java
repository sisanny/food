package com.homework.orderapplication.request;

import com.homework.orderapplication.enumValidator.ValueOfEnum;
import com.homework.orderapplication.model.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class OrderUpdateRequest {
    @NotBlank(message = "The status cannot be empty")
    @ValueOfEnum(enumClass = Status.class)
    @ApiModelProperty(value = "Status of order", example = "ready")
    private String status;
}
