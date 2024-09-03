package com.salih.service.product;


import com.salih.dto.product.ProductDto;
import com.salih.exception.CategoryNotFoundException;
import com.salih.model.entity.Brand;
import com.salih.model.entity.Category;
import com.salih.model.entity.Product;
import com.salih.repository.BrandRepository;
import com.salih.repository.CategoryRepository;
import com.salih.repository.ProductRepository;
import com.salih.result.DataResult;
import com.salih.result.Result;
import com.salih.service.category.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;


    // Çözüldü: bu metod brandleri ve categoryleri getirmiyor boş döndürüyor problemi çöz
    @Override
    public DataResult<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return new DataResult<>(null,Result.showMessage(Result.SERVER_ERROR, " Products not found"));
        }
        return new DataResult<>(products, Result.showMessage(Result.SUCCESS, " Products listed successfully"));
    }

    @Override
    public DataResult<Product> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return new DataResult<>(null, Result.showMessage(Result.NOT_FOUND, "Product not found"));
        }
        return new DataResult<>(product.get(), Result.showMessage(Result.SUCCESS, "Product found"));

    }

    @Override
    public DataResult<Product> getProductByName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        if (product.isEmpty()) {
            return new DataResult<>(null, Result.showMessage(Result.NOT_FOUND, "Product not found"));
        }
        return new DataResult<>(product.get(), Result.showMessage(Result.SUCCESS, "Product found"));
    }

    @Override
    public Result addProduct(ProductDto product) {

        Category selectedCaytegory  = categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        Brand selectedBrand = brandRepository.findById(product.getBrandId()).orElseThrow(() -> new CategoryNotFoundException("Brand not found"));

        Product addedProduct = Product.builder()
                .name(product.getName())
                .isBoycott(product.getIsBoycott())
                .barCode(product.getBarCode())
                .image(product.getImage())
                .category(selectedCaytegory )
                .brand( selectedBrand)
                .build();

        try {
            productRepository.save(addedProduct);
        } catch (Exception e) {
            return Result.showMessage(Result.SERVER_ERROR, "Product not added");
        }
        return Result.showMessage(Result.SUCCESS, "Product added");
    }

    @Override
    public Result updateProduct(Long id, ProductDto product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return Result.showMessage(Result.NOT_FOUND, "Product not found");
        }

        Category selectedCaytegory  = categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        Brand selectedBrand = brandRepository.findById(product.getBrandId()).orElseThrow(() -> new CategoryNotFoundException("Brand not found"));

        Product updatedProduct = productOptional.get();
        updatedProduct.setName(product.getName());
        updatedProduct.setIsBoycott(product.getIsBoycott());
        updatedProduct.setBarCode(product.getBarCode());
        updatedProduct.setImage(product.getImage());
        updatedProduct.setCategory(selectedCaytegory);
        updatedProduct.setBrand(selectedBrand);
        try {
            productRepository.save(updatedProduct);
        } catch (Exception e) {
            return Result.showMessage(Result.SERVER_ERROR, "Product not updated");
        }
        return Result.showMessage(Result.SUCCESS, "Product updated");
    }

    @Override
    public Result deleteProduct(Long id) {
        Boolean isExist = productRepository.existsById(id);
        if (!isExist) {
            return Result.showMessage(Result.NOT_FOUND, "Product not found");
        }
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            return Result.showMessage(Result.SERVER_ERROR, "Product not deleted");
        }
        return Result.showMessage(Result.SUCCESS, "Product deleted");

    }

}
