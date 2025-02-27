package com.project.product_service.service;

import com.project.product_service.exception.ProductNotFoundException;
import com.project.product_service.model.Product;
import com.project.product_service.model.dto.PaginatedResponse;
import com.project.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Page<Product> getAll(Pageable pageable){
        Page<Product> response = repository.findAll(pageable);
        if (response.getContent().isEmpty())
            throw new ProductNotFoundException("Not product founded, please register products!");
        return repository.findAll(pageable);
    }
}
