package com.salih.controller;


import com.salih.model.entity.Category;
import com.salih.model.entity.Category;
import com.salih.result.DataResult;
import com.salih.result.Result;
import com.salih.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/category")
public class CategoryController {
    
    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<DataResult<List<Category>>> getAllCategories(){
        DataResult<List<Category>> result = categoryService.getAll();
        if (result.getData() == null || result.getData().isEmpty()){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("get/{id}")
    public ResponseEntity<DataResult<Category>> getCategoryById(@PathVariable Long id){
        DataResult<Category> result = categoryService.getById(id);
        if (result.getData() == null){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> addCategory(@RequestBody Category category){
        Result result = categoryService.add(category);
        if (result.getResultCode() != 0){
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Result> updateCategory(@PathVariable Long id, @RequestBody Category category){
        Result result = categoryService.update(id, category);
        if (result.getResultCode() != 0){
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteCategory(@PathVariable Long id){
        Result result = categoryService.delete(id);
        if (result.getResultCode() != 3){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        if (result.getResultCode() != 0){
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    

}
