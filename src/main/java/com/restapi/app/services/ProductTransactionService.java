package com.restapi.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.restapi.app.models.entitiies.ProductTransaction;
import com.restapi.app.models.respositories.ProductTransactionRepository;

@Service
public class ProductTransactionService {
    
    @Autowired
    private ProductTransactionRepository ProductTransaction;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public int readProductQuantity(Long productId) {
        ProductTransaction product = ProductTransaction.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        return product.getQuantity();
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateProductQuantity(Long productId, int newQuantity) {
        ProductTransaction product = ProductTransaction.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setQuantity(newQuantity);
        ProductTransaction.save(product);
    }
    
}
