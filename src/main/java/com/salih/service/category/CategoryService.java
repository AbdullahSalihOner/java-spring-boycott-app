package com.salih.service.category;

import com.salih.dto.category.CategoryDto;
import com.salih.model.entity.Brand;
import com.salih.model.entity.Category;
import com.salih.repository.BrandRepository;
import com.salih.repository.CategoryRepository;
import com.salih.result.DataResult;
import com.salih.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;


    // FIXME : bu metod brandleri getirmiyor boş döndürüyor problemi çöz
    @Override
    public DataResult<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return new DataResult<>(null,Result.showMessage(Result.SERVER_ERROR, " Categories not found"));
        }
        return new DataResult<>(categories, Result.showMessage(Result.SUCCESS, " Categories listed successfully"));
    }

    // FIXME : bu metod brandleri getirmiyor boş döndürüyor problemi çöz
    @Override
    public DataResult<Category> getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            return new DataResult<>(null, Result.showMessage(Result.NOT_FOUND, "Category not found"));
        }
        return new DataResult<>(category.get(), Result.showMessage(Result.SUCCESS, "Category found"));
    }

    // Çalışıyor
    @Override
    public Result addCategory(CategoryDto category) {

        Set<Brand> selectedBrands = new HashSet<>();

        for (Long brandId : category.getBrandIds()) {
            Optional<Brand> brand = brandRepository.findById(brandId);
            if (brand.isEmpty()) {
                return Result.showMessage(Result.NOT_FOUND, "Brand not found");
            }
            selectedBrands.add(brand.get());
        }

        Category addedCategory = Category.builder()
                .name(category.getName())
                .brands(selectedBrands)
                .build();

        try {
            categoryRepository.save(addedCategory);
        } catch (Exception e) {
            return Result.showMessage(Result.SERVER_ERROR, "Category not added");
        }
        return Result.showMessage(Result.SUCCESS, "Category added");
    }

    // Çalışıyor
    @Override
    public Result updateCategory(Long id, CategoryDto category) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            return Result.showMessage(Result.NOT_FOUND, "Category not found");
        }

        Set<Brand> selectedBrands = new HashSet<>();

        for (Long brandId : category.getBrandIds()) {
            Optional<Brand> brand = brandRepository.findById(brandId);
            if (brand.isEmpty()) {
                return Result.showMessage(Result.NOT_FOUND, "Brand not found");
            }
            selectedBrands.add(brand.get());
        }

        Category updatedCategory = categoryOptional.get();
        updatedCategory.setName(category.getName());
        updatedCategory.setBrands(selectedBrands);
        categoryRepository.save(updatedCategory);
        return Result.showMessage(Result.SUCCESS, "Category updated");
    }

    // Çalışıyor
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
