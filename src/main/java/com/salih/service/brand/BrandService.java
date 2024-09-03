package com.salih.service.brand;

import com.salih.dto.brand.BrandDto;
import com.salih.model.entity.Brand;
import com.salih.model.entity.Category;
import com.salih.repository.BrandRepository;
import com.salih.repository.CategoryRepository;
import com.salih.result.DataResult;
import com.salih.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@RequiredArgsConstructor
@Service
public class BrandService implements IBrandService {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public DataResult<List<Brand>> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        if (brands.isEmpty()) {
            return new DataResult<>(null, Result.showMessage(Result.SERVER_ERROR, "Brands not found"));
        }
        return new DataResult<>(brands, Result.showMessage(Result.SUCCESS, "Brands listed successfully"));
    }

    @Override
    public DataResult<Brand> getBrandById(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isEmpty()) {
            return new DataResult<>(null, Result.showMessage(Result.NOT_FOUND, "Brand not found"));
        }
        return new DataResult<>(brand.get(), Result.showMessage(Result.SUCCESS, "Brand found"));
    }

    @Override
    public DataResult<Brand> getBrandByName(String name) {
        Optional<Brand> brandOptional = brandRepository.findByName(name);
        if (brandOptional.isEmpty()) {
            return new DataResult<>(null, Result.showMessage(Result.NOT_FOUND, "Brand not found"));
        }

        Brand brand = brandOptional.get();
        return new DataResult<>(brand, Result.showMessage(Result.SUCCESS, "Brand found successfully"));
    }

    @Override
    public Result addBrand(BrandDto brandDto) {
        Set<Category> selectedCategories = new HashSet<>();

        for (Long categoryId : brandDto.getCategoryIds()) {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isEmpty()) {
                return Result.showMessage(Result.NOT_FOUND, "Category not found");
            }
            selectedCategories.add(category.get());
        }

        Brand addedBrand = Brand.builder()
                .name(brandDto.getName())
                .logo(brandDto.getLogo())
                .proof(brandDto.getProof())
                .categories(selectedCategories)
                .build();
        try {
            brandRepository.save(addedBrand);
        } catch (Exception e) {
            return Result.showMessage(Result.SERVER_ERROR, "Brand not added");
        }
        return Result.showMessage(Result.SUCCESS, "Brand added");
    }

    @Override
    public Result updateBrand(Long id, BrandDto brandDto) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isEmpty()) {
            return Result.showMessage(Result.NOT_FOUND, "Brand not found");
        }

        Set<Category> selectedCategories = new HashSet<>();

        for (Long categoryId : brandDto.getCategoryIds()) {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isEmpty()) {
                return Result.showMessage(Result.NOT_FOUND, "Category not found");
            }
            selectedCategories.add(category.get());
        }

        Brand updatedBrand = brandOptional.get();
        updatedBrand.setName(brandDto.getName());
        updatedBrand.setLogo(brandDto.getLogo());
        updatedBrand.setCategories(selectedCategories);
        brandRepository.save(updatedBrand);
        return Result.showMessage(Result.SUCCESS, "Brand updated");
    }

    @Override
    public Result deleteBrand(Long id) {
        Boolean isExist = brandRepository.existsById(id);
        if (!isExist) {
            return Result.showMessage(Result.NOT_FOUND, "Brand not found");
        }
        try {
            brandRepository.deleteById(id);
        } catch (Exception e) {
            return Result.showMessage(Result.SERVER_ERROR, "Brand not deleted due to conflict");
        }

        return Result.showMessage(Result.SUCCESS, "Brand deleted");
    }
}