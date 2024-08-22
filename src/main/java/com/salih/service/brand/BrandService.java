package com.salih.service.brand;

import com.salih.model.entity.Brand;
import com.salih.repository.BrandRepository;
import com.salih.service.ICrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandService implements ICrudService<Brand,Long> {

    private final BrandRepository brandRepository;

    @Override
    public List<Brand> getAll() {
        return null;
    }

    @Override
    public Brand getById(Long aLong) {
        return null;
    }

    @Override
    public Brand add(Brand entity) {
        return null;
    }

    @Override
    public Brand update(Long aLong, Brand entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
