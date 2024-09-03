package com.salih.mapper;

import com.salih.dto.brand.BrandDto;
import com.salih.model.entity.Brand;
import com.salih.model.entity.Category;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BrandMapper {

    public BrandDto toDTO(Brand brand) {
       return null;
    }

    public Brand toEntity(BrandDto brandDto, Set<Category> categories) {
        return Brand.builder()
                .name(brandDto.getName())
                .proof(brandDto.getProof())
                .logo(brandDto.getLogo())
                .categories(categories)
                .build();
    }
}