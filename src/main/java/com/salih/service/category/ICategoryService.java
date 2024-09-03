package com.salih.service.category;

import com.salih.dto.category.CategoryDto;
import com.salih.model.entity.Category;
import com.salih.result.DataResult;
import com.salih.result.Result;

import java.util.List;

public interface ICategoryService {
    DataResult<List<Category>> getAllCategories();
    DataResult<Category> getCategoryById(Long id);
    Result addCategory(CategoryDto category);
    Result updateCategory(Long categoryId,CategoryDto category);
    Result deleteCategory(Long id);


}
