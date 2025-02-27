package com.project.product_service.service;

import com.project.product_service.enums.Category;
import com.project.product_service.exception.ProductNotFoundException;
import com.project.product_service.model.Product;
import com.project.product_service.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    @DisplayName("Should get all products")
    void caseGetAll1() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        Product p = Product.builder()
                .id(1L)
                .price(BigDecimal.TEN)
                .name("Pants")
                .category(Category.CLOTHES)
                .build();
        productList.add(p);

        Pageable pageable = PageRequest.of(0, 1, Sort.by("id").ascending());
        Page<Product> paginatedResponse = new PageImpl<>(productList, pageable, 1);

        when(repository.findAll(pageable)).thenReturn(paginatedResponse);

        // Action
        Page<Product> response = productService.getAll(pageable);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.getContent().size());
        assertEquals(p, response.getContent().get(0));
    }

    @Test
    @DisplayName("Should not return a list of product when it have not product registered")
    void caseGetAll2() {
        // Arrange
        List<Product> productList = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, 1, Sort.by("id").ascending());
        Page<Product> paginatedResponse = new PageImpl<>(productList, pageable, 0);

        when(repository.findAll(pageable)).thenReturn(paginatedResponse);

        // Action
        Exception thrown = Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productService.getAll(pageable);
        });

        // Assert
        Assertions.assertEquals("Not product founded, please register products!", thrown.getMessage());
    }
}