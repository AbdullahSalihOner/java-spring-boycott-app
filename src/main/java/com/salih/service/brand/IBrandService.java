package com.salih.service.brand;

import com.salih.model.entity.Brand;

import java.util.List;

public interface IBrandService {
    List<Brand> getAllBrands();
    Brand getBrandById(Long id);
    Brand addBrand(Brand brand);
    Brand updateBrand(Long BrandId,Brand brand);
    void deleteBrand(Long id);

}
