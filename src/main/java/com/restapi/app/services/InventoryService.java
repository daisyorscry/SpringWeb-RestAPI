package com.restapi.app.services;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.restapi.app.dto.Requests.Products.CreateInventoryRequest;
import com.restapi.app.dto.Requests.Products.StockChanceRequest;
import com.restapi.app.dto.Responses.ProductResponses.InventoryResponse;
import com.restapi.app.models.entitiies.Inventory;
import com.restapi.app.models.entitiies.InventoryDetails;
import com.restapi.app.models.entitiies.Product;
import com.restapi.app.models.respositories.InventoryDetailsRepository;
import com.restapi.app.models.respositories.InventoryRepository;
import com.restapi.app.models.respositories.ProductRepository;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryDetailsRepository inventoryDetailsRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(inventory -> {
            Product product = productRepository.findById(inventory.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            return toInventoryResponse(product, inventory);
        }).collect(Collectors.toList());
    }

    public InventoryResponse toInventoryResponse(Product product, Inventory inventory) {
        return InventoryResponse.builder()
            .inventoryId(inventory.getId())
            .productId(product.getId())
            .productName(product.getProduct_name())
            .productDesc(product.getProduct_desc())
            .price(inventory.getPrice())
            .createdAt(product.getCreatedAt())
            .build();
    }

    @Transactional
    public void createInventory(CreateInventoryRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setPrice(request.getPrice());

        inventoryRepository.save(inventory);
    }

    @Transactional(rollbackFor = Exception.class)
    public void chanceStock(StockChanceRequest request) {
        Inventory inventory = inventoryRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Inventory not found")); 
        
        int newStock = request.getQuantity();
    
        if ("REMOVE".equalsIgnoreCase(request.getChanceType())) {
            newStock = -newStock; 
        } else if (!"ADD".equalsIgnoreCase(request.getChanceType())) {
            throw new RuntimeException("Invalid chance type"); 
        }
    
        InventoryDetails existingDetails = inventoryDetailsRepository.findByInventory(inventory)
                .orElseGet(() -> {
                    InventoryDetails newDetails = new InventoryDetails();
                    newDetails.setInventory(inventory);
                    newDetails.setStock(0); 
                    return newDetails;
                });
    
        int currentStock = existingDetails.getStock();
        int updatedStock = currentStock + newStock;
        existingDetails.setStock(updatedStock);

    if ("REMOVE".equalsIgnoreCase(request.getChanceType()) && updatedStock < 0) {
        throw new RuntimeException("Insufficient stock. Available: " + currentStock + ", Requested: " + Math.abs(request.getQuantity()));
    }
    
    if (updatedStock < 0) {
            throw new RuntimeException("Stock LOSS");
        }

        inventoryDetailsRepository.save(existingDetails);
    }
    
}
