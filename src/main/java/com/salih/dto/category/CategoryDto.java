package com.salih.dto.category;

import com.salih.dto.brand.BrandDto;
import com.salih.model.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private String name;
    private Set<Long> brandIds;

}
