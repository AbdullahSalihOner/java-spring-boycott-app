package com.salih.service.product;

import com.salih.model.entity.Product;
import com.salih.result.DataResult;
import com.salih.result.Result;

import java.util.List;

public interface IProductService {
    DataResult<List<Product>> getAllProducts();
    DataResult<Product> getProductById(Long id);
    Result addProduct(Product product);
    Result updateProduct(Long productId,Product product);
    Result deleteProduct(Long id);

}
