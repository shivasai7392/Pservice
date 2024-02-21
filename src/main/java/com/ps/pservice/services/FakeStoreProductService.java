package com.ps.pservice.services;

import com.ps.pservice.dtos.FakeStoreProductDto;
import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements IProductService {

    private String getProductUrl = "https://fakestoreapi.com/products/{id}";
    private String getAllProductsUrl = "https://fakestoreapi.com/products";
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService() {
        this.restTemplateBuilder = new RestTemplateBuilder();
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        GenericProductDto genericProductDto = new GenericProductDto();

        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        if (fakeStoreProductDto != null) {
            genericProductDto = getGenericProductDto(fakeStoreProductDto);
        }

        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(getAllProductsUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = responseEntity.getBody();
        if (fakeStoreProductDtos != null) {
            for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
                GenericProductDto genericProductDto = getGenericProductDto(fakeStoreProductDto);
                genericProductDtos.add(genericProductDto);
            }
        }

        return genericProductDtos;
    }

    private static GenericProductDto getGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        return genericProductDto;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        GenericProductDto genericProductDto = new GenericProductDto();
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(getProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = null;
        if (responseEntity != null) {
            fakeStoreProductDto = responseEntity.getBody();
        }
        if (fakeStoreProductDto != null) {
            genericProductDto = getGenericProductDto(fakeStoreProductDto);
        }
        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(getAllProductsUrl, product, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        if (fakeStoreProductDto != null) {
            genericProductDto = getGenericProductDto(fakeStoreProductDto);
        }
        return genericProductDto;
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        RestTemplate restTemplate = this.restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(getProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = null;
        if (responseEntity != null) {
            fakeStoreProductDto = responseEntity.getBody();
        }
        if (fakeStoreProductDto != null) {
            genericProductDto = getGenericProductDto(fakeStoreProductDto);
        }
        return genericProductDto;

    }
}
