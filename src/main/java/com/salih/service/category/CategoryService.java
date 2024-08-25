package com.salih.service.category;

import com.salih.model.entity.Category;
import com.salih.repository.CategoryRepository;
import com.salih.result.DataResult;
import com.salih.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public DataResult<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return new DataResult<>(null,Result.showMessage(Result.SERVER_ERROR, " Categories not found"));
        }
        return new DataResult<>(categories, Result.showMessage(Result.SUCCESS, " Categories listed successfully"));
    }

    @Override
    public DataResult<Category> getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            return new DataResult<>(null, Result.showMessage(Result.NOT_FOUND, "Category not found"));
        }
        return new DataResult<>(category.get(), Result.showMessage(Result.SUCCESS, "Category found"));
    }

    @Override
    public Result addCategory(Category category) {
        Category addedCategory = Category.builder()
                .name(category.getName())
                .brands(category.getBrands())
                .build();

        try {
            categoryRepository.save(addedCategory);
        } catch (Exception e) {
            return Result.showMessage(Result.SERVER_ERROR, "Category not added");
        }
        return Result.showMessage(Result.SUCCESS, "Category added");
    }

    @Override
    public Result updateCategory(Long id, Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            return Result.showMessage(Result.NOT_FOUND, "Category not found");
        }
        Category updatedCategory = categoryOptional.get();
        updatedCategory.setName(category.getName());
        updatedCategory.setBrands(category.getBrands());
        categoryRepository.save(updatedCategory);
        return Result.showMessage(Result.SUCCESS, "Category updated");
    }

    @Override
    public Result deleteCategory(Long id) {
        Boolean isExist = categoryRepository.existsById(id);
        if (!isExist) {
            return Result.showMessage(Result.NOT_FOUND, "Category not found");
        }
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            return Result.showMessage(Result.SERVER_ERROR, "Category not deleted");
        }
        return Result.showMessage(Result.SUCCESS, "Category deleted");
    }
}
