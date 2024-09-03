package com.salih.service.brand;

import com.salih.dto.brand.BrandDto;
import com.salih.model.entity.Brand;
import com.salih.result.DataResult;
import com.salih.result.Result;

import java.util.List;

public interface IBrandService {
    DataResult<List<Brand>> getAllBrands();
    DataResult<Brand> getBrandById(Long id);
    DataResult<Brand> getBrandByName(String name);
    Result addBrand(BrandDto brand);
    Result updateBrand(Long brandId,BrandDto brand);
    Result deleteBrand(Long id);

}