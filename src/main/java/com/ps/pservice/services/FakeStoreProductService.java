package com.ps.pservice.services;

import com.ps.pservice.dtos.FakeStoreProductDto;
import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements IProductService {

    private String getProductUrl = "https://fakestoreapi.com/products/1";
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService() {
        this.restTemplateBuilder = new RestTemplateBuilder();
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        GenericProductDto genericProductDto = new GenericProductDto();
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        if (fakeStoreProductDto != null) {
            genericProductDto.setId(fakeStoreProductDto.getId());
            genericProductDto.setTitle(fakeStoreProductDto.getTitle());
            genericProductDto.setDescription(fakeStoreProductDto.getDescription());
            genericProductDto.setPrice(fakeStoreProductDto.getPrice());
            genericProductDto.setImage(fakeStoreProductDto.getImage());
            genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        }


        return genericProductDto;
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
