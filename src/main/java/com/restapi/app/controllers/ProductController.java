package com.restapi.app.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.app.dto.ResponseData;
import com.restapi.app.models.Requests.Products.CreateProductRequest;
import com.restapi.app.models.Requests.Products.UpdateProductRequest;
import com.restapi.app.models.Responses.ProductResponses.ProductResponse;
import com.restapi.app.models.entitiies.User;
import com.restapi.app.services.ProductService;

import jakarta.validation.Valid;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseData<List<ProductResponse>> getAllProducts(User user) 
    {
        List<ProductResponse> productResponse = productService.getAllProducts();

        return ResponseData.<List<ProductResponse>>builder()
            .data(productResponse)
            .errors(null)
            .success(true)
            .message("Products fetched successfully")
            .build();
            
    }

    @PutMapping("/{id}")
    public ResponseData<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest request,
            User user) 
    {
        
        Optional<ProductResponse> productResponse = productService.updateProduct(id, user, request);

        if (productResponse.isPresent()) {
            return ResponseData.<ProductResponse>builder()
                .data(productResponse.get())
                .errors(null)
                .success(true)
                .message("Product updated successfully")
                .build();
        } else {
            return ResponseData.<ProductResponse>builder()
                .data(null)
                .errors(Map.of("id", "Product not found"))
                .success(false)
                .message("Product not found")
                .build();
        }
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
     public ResponseData<ProductResponse> save(@RequestBody @Valid CreateProductRequest request, BindingResult result, User user) 
     {
        
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

            return ResponseData.<ProductResponse>builder()
                    .data(null)
                    .errors(errors)
                    .success(false)
                    .message("Validation errors")
                    .build();
        }

        ProductResponse productResponse = productService.save(user, request);

        return ResponseData.<ProductResponse>builder()
                .data(productResponse)
                .errors(null)
                .success(true)
                .message("Product created successfully")
                .build();
    }

    @GetMapping("/{id}")
    public ResponseData<ProductResponse> findById(User user, @PathVariable Long id) 
    {
        Optional<ProductResponse> productResponse = productService.findById(id);
    
        if (productResponse.isPresent()) {
            return ResponseData.<ProductResponse>builder()
                .data(productResponse.get())
                .errors(null)
                .success(true)
                .message("Product fetched successfully")
                .build();
        } else {
            return ResponseData.<ProductResponse>builder()
                .data(null)
                .errors(Map.of("id", "Product not found"))
                .success(false)
                .message("Product not found")
                .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<Void> deleteProduct(User user, @PathVariable Long id) 
    {
        boolean isDeleted = productService.deleteProduct(id);
    
        if (isDeleted) {
            return ResponseData.<Void>builder()
                .data(null)
                .errors(null)
                .success(true)
                .message("Product deleted successfully")
                .build();
        } else {
            return ResponseData.<Void>builder()
                .data(null)
                .errors(Map.of("id", "Product not found"))
                .success(false)
                .message("Product not found")
                .build();
        }
    }
}
