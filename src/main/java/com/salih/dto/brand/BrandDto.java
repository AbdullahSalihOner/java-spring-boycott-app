package com.salih.dto.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandDto {

    @NotBlank
    @NotNull
    private String name;
    private String logo;
    private String proof;
    @NotBlank
    @NotNull
    private Set<Long> categoryIds;

}