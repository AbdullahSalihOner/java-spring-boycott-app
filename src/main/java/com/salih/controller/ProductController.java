package com.salih.controller;

import com.salih.dto.product.ProductDto;
import com.salih.model.entity.Product;
import com.salih.result.DataResult;
import com.salih.result.Result;
import com.salih.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {


    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<DataResult<List<Product>>> getAllProducts(){
        DataResult<List<Product>> result = productService.getAllProducts();
        if (result.getData() == null || result.getData().isEmpty()){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //@GetMapping("") //@RequestParam is work like this: /api/product?id=1
    @GetMapping("get/{id}")
    public ResponseEntity<DataResult<Product>> getProductById(@PathVariable Long id){
        DataResult<Product> result = productService.getProductById(id);
        if (result.getData() == null){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/getProductByName")
    public DataResult<Product> getProductByName(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        if (name == null || name.isEmpty()) {
            return new DataResult<>(null, Result.showMessage(Result.BAD_REQUEST, "Product name must be provided"));
        }

        return productService.getProductByName(name);
    }



    @PostMapping("/add")
    public ResponseEntity<Result> addProduct(@RequestBody ProductDto product){
        Result result = productService.addProduct(product);
        if (result.getResultCode() != 0){
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Result> updateProduct(@PathVariable Long id, @RequestBody ProductDto product){
        Result result = productService.updateProduct(id, product);
        if (result.getResultCode() != 0){
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteProduct(@PathVariable Long id){
        Result result = productService.deleteProduct(id);
        if (result.getResultCode() != 3){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        if (result.getResultCode() != 0){
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
