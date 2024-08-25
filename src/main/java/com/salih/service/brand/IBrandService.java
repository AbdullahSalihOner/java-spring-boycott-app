package com.salih.service.brand;

import com.salih.model.entity.Brand;
import com.salih.result.DataResult;
import com.salih.result.Result;

import java.util.List;

public interface IBrandService {
    DataResult<List<Brand>> getAllBrands();
    DataResult<Brand> getBrandById(Long id);
    Result addBrand(Brand brand);
    Result updateBrand(Long brandId,Brand brand);
    Result deleteBrand(Long id);

}
