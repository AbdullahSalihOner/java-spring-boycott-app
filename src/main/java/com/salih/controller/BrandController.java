package com.salih.controller;


import com.salih.dto.brand.BrandDto;
import com.salih.model.entity.Brand;
import com.salih.result.DataResult;
import com.salih.result.Result;
import com.salih.service.brand.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brand")
public class BrandController {
    
    private final BrandService brandService;

    @GetMapping("/all")
    public ResponseEntity<DataResult<List<Brand>>> getAllBrands(){
        DataResult<List<Brand>> result = brandService.getAllBrands();
        if (result.getData() == null || result.getData().isEmpty()){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //@GetMapping("") //@RequestParam is work like this: /api/brand?id=1
    @GetMapping("get/{id}")
    public ResponseEntity<DataResult<Brand>> getBrandById(@PathVariable Long id){
        DataResult<Brand> result = brandService.getBrandById(id);
        if (result.getData() == null){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> addBrand(@RequestBody BrandDto brand){
        Result result = brandService.addBrand(brand);
        if (result.getResultCode() != 0){
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Result> updateBrand(@PathVariable Long id, @RequestBody BrandDto brand){
        Result result = brandService.updateBrand(id, brand);
        if (result.getResultCode() != 0){
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteBrand(@PathVariable Long id){
        Result result = brandService.deleteBrand(id);
        if (result.getResultCode() != 3){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        if (result.getResultCode() != 0){
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    

}
