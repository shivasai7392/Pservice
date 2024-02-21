package com.ps.pservice.controllers;

import com.ps.pservice.dtos.FakeStoreProductDto;
import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.services.FakeStoreProductService;
import com.ps.pservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){

        return this.productService.getProductById(id);
    }

    @GetMapping("/")
    public void getAllProducts(){

    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable String id){

    }

    public void createProduct(){

    }

    public void updateProductById(){

    }
}
