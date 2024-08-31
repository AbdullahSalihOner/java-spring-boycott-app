package com.salih.service.brand;

import com.salih.dto.brand.BrandDto;
import com.salih.model.entity.Brand;
import com.salih.model.entity.Category;
import com.salih.repository.BrandRepository;
import com.salih.repository.CategoryRepository;
import com.salih.result.DataResult;
import com.salih.result.Result;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class BrandService implements IBrandService {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;


    // FIXME : bu metod bazılarının categorylerini getirir bazılarının getirmez problemi çöz
    @Override
    public DataResult<List<Brand>> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        if (brands.isEmpty()) {
            return new DataResult<>(null,Result.showMessage(Result.SERVER_ERROR, " Brands not found"));
        }
        return new DataResult<>(brands, Result.showMessage(Result.SUCCESS, " Brands listed successfully"));
    }


    // FIXME : bu metod categoryleri getirmiyor boş döndürüyor problemi çöz

    @Override
    public DataResult<Brand> getBrandById(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isEmpty()) {
            return new DataResult<>(null, Result.showMessage(Result.NOT_FOUND, "Brand not found"));
        }


        return new DataResult<>(brand.get(), Result.showMessage(Result.SUCCESS, "Brand found"));

    }


    // FIXME : bazen category eklemiyor
    @Override
    public Result addBrand(BrandDto brand) {

        Set<Category> selectedCategories = new HashSet<>();

        for (Long categoryId : brand.getCategoryIds()) {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isEmpty()) {
                return Result.showMessage(Result.NOT_FOUND, "Category not found");
            }
            selectedCategories.add(category.get());
        }

        Brand addedBrand = Brand.builder()
                .name(brand.getName())
                .logo(brand.getLogo())
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
    public Result updateBrand(Long id, BrandDto brand) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isEmpty()) {
            return Result.showMessage(Result.NOT_FOUND, "Brand not found");
        }

        Set<Category> selectedCategories = new HashSet<>();

        for (Long categoryId : brand.getCategoryIds()) {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isEmpty()) {
                return Result.showMessage(Result.NOT_FOUND, "Category not found");
            }
            selectedCategories.add(category.get());
        }

        Brand updatedBrand = brandOptional.get();
        updatedBrand.setName(brand.getName());
        updatedBrand.setLogo(brand.getLogo());
        updatedBrand.setCategories(selectedCategories);
        brandRepository.save(updatedBrand);
        return Result.showMessage(Result.SUCCESS, "Brand updated");
    }

    // FIXME : silme işlemi gerçekleşmiyor çakışma hatası var
    @Override
    public Result deleteBrand(Long id) {
        Boolean isExist = brandRepository.existsById(id);
        if (!isExist) {
            return Result.showMessage(Result.NOT_FOUND, "Brand not found");
        }
        brandRepository.deleteById(id);
        return Result.showMessage(Result.SUCCESS, "Brand deleted");
    }
}
