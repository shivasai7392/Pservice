package com.ps.pservice.services;

import com.ps.pservice.models.Product;

public interface IProductService {
    public Product getProductById(Long id);
    public void getAllProducts();
    public void deleteProductById(Long id);
    public void createProduct(Product product);
    public void updateProductById(Long id, Product product);
}
