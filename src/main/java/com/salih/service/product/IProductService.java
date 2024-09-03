package com.salih.service.product;

import com.salih.dto.product.ProductDto;
import com.salih.model.entity.Product;
import com.salih.result.DataResult;
import com.salih.result.Result;

import java.util.List;

public interface IProductService {
    DataResult<List<Product>> getAllProducts();
    DataResult<Product> getProductById(Long id);
    DataResult<Product> getProductByName(String name);
    Result addProduct(ProductDto product);
    Result updateProduct(Long productId,ProductDto product);
    Result deleteProduct(Long id);

}
