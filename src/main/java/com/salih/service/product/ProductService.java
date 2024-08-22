package com.salih.service.product;

import com.salih.model.entity.Product;
import com.salih.repository.ProductRepository;
import com.salih.service.ICrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService implements ICrudService<Product,Long> {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(Long aLong) {
        return null;
    }

    @Override
    public Product add(Product entity) {
        return null;
    }

    @Override
    public Product update(Long aLong, Product entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
