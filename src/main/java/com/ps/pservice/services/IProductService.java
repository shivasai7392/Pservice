package com.ps.pservice.services;

import com.ps.pservice.dtos.FakeStoreProductDto;
import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.models.Product;

public interface IProductService {
    public GenericProductDto getProductById(Long id);
    public void getAllProducts();
    public void deleteProductById(Long id);
    public void createProduct(Product product);
    public void updateProductById(Long id, Product product);
}
