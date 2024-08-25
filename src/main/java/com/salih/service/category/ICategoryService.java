package com.salih.service.category;

import com.salih.model.entity.Category
;
import com.salih.result.DataResult;
import com.salih.result.Result;

import java.util.List;

public interface ICategoryService {
    DataResult<List<Category>> getAllCategories();
    DataResult<Category> getCategoryById(Long id);
    Result addCategory(Category category);
    Result updateCategory(Long categoryId,Category category);
    Result deleteCategory(Long id);


}
