package com.ps.pservice.services;

import com.ps.pservice.dtos.FakeStoreProductDto;
import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.exceptions.ProductNotFoundException;
import com.ps.pservice.security.JWTObject;
import com.ps.pservice.security.TokenValidator;
import com.ps.pservice.thirdpartyclients.FakeStoreProductsApi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("fakeStoreProductService")
public class ProductService implements IProductService {

    private final FakeStoreProductsApi fakeStoreProductsApi;

    private final TokenValidator tokenValidator;

    public ProductService(FakeStoreProductsApi fakeStoreProductsApi, TokenValidator tokenValidator) {
        this.fakeStoreProductsApi = fakeStoreProductsApi;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public GenericProductDto getProductById(String authToken, Long id) throws ProductNotFoundException {
        Optional<JWTObject> jwtObject1 = this.tokenValidator.validateToken(authToken);
        Optional<JWTObject> jwtObjectOptional = jwtObject1;
        if (jwtObjectOptional.isEmpty()){
            return null;
        }
        JWTObject jwtObject = jwtObjectOptional.get();
        System.out.println(jwtObject);
        return getGenericProductDto(fakeStoreProductsApi.getProductById(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts() throws ProductNotFoundException {
        List<GenericProductDto> genericProducts = new ArrayList<>();

        List<FakeStoreProductDto> fakeStoreProducts = fakeStoreProductsApi.getAllProducts();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProducts) {
            GenericProductDto genericProductDto = getGenericProductDto(fakeStoreProductDto);
            genericProducts.add(genericProductDto);
        }

        return genericProducts;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws ProductNotFoundException{
        return getGenericProductDto(fakeStoreProductsApi.deleteProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return getGenericProductDto(fakeStoreProductsApi.createProduct(product));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) throws ProductNotFoundException {
        return getGenericProductDto(fakeStoreProductsApi.updateProductById(id, product));
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
}
