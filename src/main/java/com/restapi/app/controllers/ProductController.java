package com.restapi.app.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.app.models.entitiies.Product;
import com.restapi.app.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) 
    {
        this.productService = productService;
    }

    // Endpoint untuk mendapatkan semua produk
    @GetMapping
    public List<Product> getAllProducts() 
    {
        return productService.getAllProducts();
    }

    // Endpoint untuk mendapatkan produk berdasarkan ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) 
    {
        return productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // Endpoint untuk membuat produk baru
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveOrUpdateProduct(product);
    }

    // Endpoint untuk mengupdate produk
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.saveOrUpdateProduct(product);
    }

    // Endpoint untuk menghapus produk berdasarkan ID
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
