package com.salih.mapper;

import com.salih.dto.category.CategoryDto;
import com.salih.model.entity.Brand;
import com.salih.model.entity.Category;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto toDTO(Category category) {
        return null;

    }

    public Category toEntity(CategoryDto categoryDto, Set<Brand> brands) {
        return Category.builder()
                .name(categoryDto.getName())
                .brands(brands)
                .build();
    }
}
