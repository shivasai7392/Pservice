package com.ps.pservice.thirdpartyclients;

import com.ps.pservice.dtos.FakeStoreProductDto;
import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FakeStoreProductsApi {

    private String getProductUrl;
    private String getAllProductsUrl;
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductsApi(@Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                @Value("${fakestore.api.path.products}") String pathForProducts,
                                @Value("${fakestore.api.path.categories}") String pathForCategories,
                                @Value("${fakestore.api.path.category}") String pathForCategory) {
        this.restTemplateBuilder = new RestTemplateBuilder();
        this.getProductUrl = fakeStoreApiUrl+pathForProducts+"/{id}";
        this.getAllProductsUrl = fakeStoreApiUrl+pathForProducts;
    }

    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class, id);
        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product with id: " + id + " not found");
        }
        return responseEntity.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(getAllProductsUrl, FakeStoreProductDto[].class);
        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Products are not found");
        }
        return List.of(Objects.requireNonNull(responseEntity.getBody()));
    }

    public FakeStoreProductDto deleteProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(getProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        if (responseEntity != null && responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product with id: " + id + " not found");
        }
        return responseEntity.getBody();
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(getAllProductsUrl, product, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }

    public FakeStoreProductDto updateProductById(Long id, GenericProductDto product) throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(getProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);
        if (responseEntity != null && responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product with id: " + id + " not found");
        }
        return responseEntity.getBody();
    }
}
