package com.ravindra.product_management.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class CategoryDto {


    @NotBlank(message = "categary id is requered")

    private Long categaryId;

    private String categaryName;
    private String description;
}
