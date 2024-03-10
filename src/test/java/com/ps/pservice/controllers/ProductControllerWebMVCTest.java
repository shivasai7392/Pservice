package com.ps.pservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.pservice.PServiceApplication;
import com.ps.pservice.dtos.GenericProductDto;
import com.ps.pservice.exceptions.ProductNotFoundException;
import com.ps.pservice.services.IProductService;
import com.ps.pservice.services.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductController.class)
public class ProductControllerWebMVCTest {

    @MockBean(name = "fakeStoreProductService")
    private IProductService productService;

    @Inject
    private MockMvc mockMvc;

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private ProductController productController;

    @Captor
    private ArgumentCaptor<Long> longArgumentCaptor;

    @Test
    public void testGetAllProductsEmptyList() throws Exception {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllProductsRetuensValidList() throws Exception {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        genericProductDtos.add(new GenericProductDto());
        genericProductDtos.add(new GenericProductDto());
        genericProductDtos.add(new GenericProductDto());
        when(productService.getAllProducts()).thenReturn(genericProductDtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(genericProductDtos)));
    }

    @Test
    public void testCreateProduct() throws Exception {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle("Test Product");
        genericProductDto.setDescription("Test Description");
        genericProductDto.setPrice(100L);
        genericProductDto.setImage("test.jpg");
        genericProductDto.setCategory("Test Category");

        GenericProductDto outputGenericProductDto = new GenericProductDto();
        outputGenericProductDto.setTitle(genericProductDto.getTitle());
        outputGenericProductDto.setDescription(genericProductDto.getDescription());
        outputGenericProductDto.setPrice(genericProductDto.getPrice());
        outputGenericProductDto.setImage(genericProductDto.getImage());
        outputGenericProductDto.setCategory(genericProductDto.getCategory());
        outputGenericProductDto.setId(1L);

        when(productService.createProduct(any())).thenReturn(outputGenericProductDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(genericProductDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(outputGenericProductDto)))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Product"));
    }

    @Test
    public void testIfSameInput() throws ProductNotFoundException {
        long id = 1L;

        when(productService.getProductById(id)).thenReturn(new GenericProductDto());
        GenericProductDto genericProductDto = productController.getProductById(id);

        verify(productService).getProductById(longArgumentCaptor.capture());
        assertEquals(id, longArgumentCaptor.getValue());
    }

}
