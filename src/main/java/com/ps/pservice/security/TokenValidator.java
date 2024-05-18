package com.ps.pservice.security;

import com.ps.pservice.dtos.FakeStoreProductDto;
import com.ps.pservice.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class TokenValidator {
    private final RestTemplateBuilder restTemplateBuilder;
    private final String tokenValidateUrl;

    public TokenValidator(RestTemplateBuilder restTemplateBuilder, @Value("${validate.token.api.url}") String tokenValidateUrl) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.tokenValidateUrl = tokenValidateUrl;
    }

    public Optional<JWTObject> validateToken(String authToken) throws ProductNotFoundException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        JWTObject jwtObject = new JWTObject();
        jwtObject.setToken(authToken);

        ResponseEntity<Optional> responseEntity = restTemplate.postForEntity(tokenValidateUrl, jwtObject, Optional.class);
        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Invalid Token");
        }
        return responseEntity.getBody();
    }
}
