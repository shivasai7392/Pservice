package com.ps.pservice.controllers;

import com.ps.pservice.services.FakeStoreProductService;
import com.ps.pservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id){
        return "Product with id "+id+" is fetched";
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
