package com.restapi.app.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restapi.app.models.Requests.Products.CreateProductRequest;
import com.restapi.app.models.Requests.Products.UpdateProductRequest;
import com.restapi.app.models.Responses.ProductResponses.ProductResponse;
import com.restapi.app.models.entitiies.Product;
import com.restapi.app.models.entitiies.User;
import com.restapi.app.models.respositories.ProducsRepository;

@Service
public class ProductService {

    @Autowired
    private ProducsRepository productsRepository;

    @Autowired
    private ValidationService validationService;
    
    public ProductResponse save(User user, CreateProductRequest request ) 
    {
        validationService.validate(request);
        
        Product product = new Product();
        product.setProduct_name(request.getProduct_name());
        product.setProduct_desc(request.getProduct_desc());
        product.setCreatedBy(user);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedBy(user);
        product.setUpdatedAt(LocalDateTime.now());
        productsRepository.save(product);

        return toProductResponse(product);
    }

    public Optional<ProductResponse> updateProduct(Long id, User user, UpdateProductRequest request) 
    {
        validationService.validate(request);
        Optional<Product> existingProduct = productsRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setProduct_name(request.getProduct_name());
            product.setProduct_desc(request.getProduct_desc());
            product.setUpdatedBy(user);
            product.setUpdatedAt(LocalDateTime.now());
            productsRepository.save(product);
            return Optional.of(toProductResponse(product));
        }
        return Optional.empty();
    }

    private ProductResponse toProductResponse(Product product)
    {
        return ProductResponse.builder()
            .productName(product.getProduct_name())
            .productDesc(product.getProduct_desc())
            .createdBy(product.getCreatedBy().getUsername())
            .createdAt(product.getCreatedAt().toString())
            .updatedBy(product.getUpdatedBy().getUsername())
            .updatedAt(product.getUpdatedAt().toString())
            .build();
    }
    
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() 
    {
        List<Product> products = productsRepository.findAll();
        return products.stream()
            .map(this::toProductResponse).toList();
    }

    @Transactional(readOnly = true)
    public Optional<ProductResponse> findById(Long id) 
    {
        Optional<Product> product = productsRepository.findById(id);
        return product.map(this::toProductResponse);
    }

    @Transactional
    public boolean deleteProduct(Long id) 
    {
        Optional<Product> product = productsRepository.findById(id);
        if (product.isPresent()) {
            productsRepository.delete(product.get());
            return true;
        }
        return false;
    }
}
