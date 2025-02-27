package com.project.product_service.controller;

import com.project.product_service.model.Product;
import com.project.product_service.model.dto.PaginatedResponse;
import com.project.product_service.service.ProductService;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;

    @Test
    @DisplayName("Should return status 200 and a list")
    public void getAll() throws Exception {
        // Arrange
        List<Product> productList = new ArrayList<>();
        Product p = Product.builder().build();
        productList.add(p);

        Page<Product> paginatedResponse = new PageImpl<>(productList, PageRequest.of(1, 1), 1);

        when(service.getAll(any(Pageable.class))).thenReturn(paginatedResponse);

        // Action
        ResultActions response = mockMvc.perform(get("/api/product/")
                .param("page", "1")
                .param("size", "1")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(jsonPath("$.size", is(paginatedResponse.getSize())))
                .andExpect(jsonPath("$.number", is(paginatedResponse.getNumber())))
                .andExpect(jsonPath("$.totalElements", is((int) paginatedResponse.getTotalElements())))
                .andExpect(jsonPath("$.totalPages", is(paginatedResponse.getTotalPages())));
    }
}