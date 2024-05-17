package com.ps.pservice.controllers;

import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.exceptions.ProductNotFoundException;
import com.ps.pservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathVariable("id") Long id) throws ProductNotFoundException {
        return this.productService.getProductById(authToken, id);
    }

    @GetMapping("")
    public List<GenericProductDto> getAllProducts() throws ProductNotFoundException{
        return this.productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable Long id) throws ProductNotFoundException{
        return this.productService.deleteProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return this.productService.createProduct(genericProductDto);
    }

    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) throws ProductNotFoundException {
        return this.productService.updateProductById(id, genericProductDto);
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
//        System.out.println("No product found with the given id.");
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessage("No product found with the given id.");
//        exceptionDto.setStatus(HttpStatus.NOT_FOUND);
//
//        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
//    }
}
