package com.ps.pservice.services;

import com.ps.pservice.dtos.FakeStoreProductDto;
import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.exceptions.ProductNotFoundException;
import com.ps.pservice.models.Product;

import java.util.List;

public interface IProductService {
    public GenericProductDto getProductById(Long id) throws ProductNotFoundException;
    public List<GenericProductDto> getAllProducts() throws ProductNotFoundException;
    public GenericProductDto deleteProductById(Long id) throws ProductNotFoundException;
    public GenericProductDto createProduct(GenericProductDto product);
    public GenericProductDto updateProductById(Long id, GenericProductDto product) throws ProductNotFoundException;
}
