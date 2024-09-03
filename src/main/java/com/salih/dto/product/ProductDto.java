package com.salih.dto.product;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class ProductDto {

    @NotBlank
    private String name;
    private Boolean isBoycott;
    private String barCode;
    private String image;
    private Long categoryId;
    private Long brandId;


}