package com.restapi.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restapi.app.models.entitiies.Product;
import com.restapi.app.models.respositories.ProductsRepository;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }
    
    // Menyimpan atau mengupdate produk
    public Product saveOrUpdateProduct(Product product) {
        return productsRepository.save(product);
    }
    
    // Mengambil semua produk
    public List<Product> getAllProducts() {
        return (List<Product>) productsRepository.findAll();
    }
    
    // Mengambil produk berdasarkan ID
    public Optional<Product> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    // Menghapus produk berdasarkan ID
    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }
}
