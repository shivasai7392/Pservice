package com.ps.pservice.services;

import com.ps.pservice.dtos.FakeStoreProductDto;
import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.models.Product;

import java.util.List;

public interface IProductService {
    public GenericProductDto getProductById(Long id);
    public List<GenericProductDto> getAllProducts();
    public GenericProductDto deleteProductById(Long id);
    public GenericProductDto createProduct(GenericProductDto product);
    public GenericProductDto updateProductById(Long id, GenericProductDto product);
}
