package com.salih.service.brand;

import com.salih.model.entity.Brand;
import com.salih.repository.BrandRepository;
import com.salih.result.DataResult;
import com.salih.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BrandService implements IBrandService {

    private final BrandRepository brandRepository;


    @Override
    public DataResult<List<Brand>> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        if (brands.isEmpty()) {
            return new DataResult<>(null,Result.showMessage(Result.SERVER_ERROR, " Brands not found"));
        }
        return new DataResult<>(brands, Result.showMessage(Result.SUCCESS, " Brands listed successfully"));
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
    public Result addBrand(Brand brand) {
        Brand addedBrand = Brand.builder()
                .name(brand.getName())
                .logo(brand.getLogo())
                .categories(brand.getCategories())
                .build();

        try {
            brandRepository.save(addedBrand);
        } catch (Exception e) {
            return Result.showMessage(Result.SERVER_ERROR, "Brand not added");
        }
        return Result.showMessage(Result.SUCCESS, "Brand added");
    }

    @Override
    public Result updateBrand(Long id, Brand brand) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isEmpty()) {
            return Result.showMessage(Result.NOT_FOUND, "Brand not found");
        }
        Brand updatedBrand = brandOptional.get();
        updatedBrand.setName(brand.getName());
        updatedBrand.setLogo(brand.getLogo());
        updatedBrand.setCategories(brand.getCategories());
        brandRepository.save(updatedBrand);
        return Result.showMessage(Result.SUCCESS, "Brand updated");
    }

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
