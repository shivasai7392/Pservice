package com.ps.pservice.services;

import com.ps.pservice.models.Product;
import org.springframework.stereotype.Service;

@Service("selfProductService")
public class SelfProductService implements IProductService{
    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public void getAllProducts() {

    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public void createProduct(Product product) {

    }

    @Override
    public void updateProductById(Long id, Product product) {

    }
}
