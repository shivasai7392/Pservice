package com.ps.pservice.controllers;

import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.exceptions.ProductNotFoundException;
import com.ps.pservice.services.IProductService;
import com.ps.pservice.services.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @MockBean
    private IProductService productService;

    @Inject
    private ProductController productController;

    @Test
    public void testGetProductById() throws ProductNotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        when(productService.getProductById(1L)).thenReturn(genericProductDto);
        GenericProductDto genericProductDto1 = productController.getProductById(1L);
        assertEquals(genericProductDto, genericProductDto1);
    }

//    @GetMapping("")
//    public List<GenericProductDto> getAllProducts() throws ProductNotFoundException{
//        return this.productService.getAllProducts();
//    }
//
//    @DeleteMapping("/{id}")
//    public GenericProductDto deleteProductById(@PathVariable Long id) throws ProductNotFoundException{
//        return this.productService.deleteProductById(id);
//    }
//
//    @PostMapping
//    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
//        return this.productService.createProduct(genericProductDto);
//    }
//
//    @PutMapping("/{id}")
//    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) throws ProductNotFoundException {
//        return this.productService.updateProductById(id, genericProductDto);
//    }
}
