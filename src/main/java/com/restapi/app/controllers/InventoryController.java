package com.restapi.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.app.dto.ResponseData;
import com.restapi.app.dto.Requests.Products.CreateInventoryRequest;
import com.restapi.app.dto.Requests.Products.StockChanceRequest;
import com.restapi.app.dto.Responses.ProductResponses.InventoryResponse;
import com.restapi.app.services.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;


    @PostMapping("/create")
    public ResponseEntity<String> createInventory(@RequestBody CreateInventoryRequest request) {
        inventoryService.createInventory(request);
        return ResponseEntity.ok("create inventory success");
    }

    @GetMapping
    public ResponseData<List<InventoryResponse>> getAllInventory() {
        List<InventoryResponse> inventoryResponses = inventoryService.getAllInventory();
            return ResponseData.<List<InventoryResponse>>builder()
            .data(inventoryResponses)
            .errors(null)
            .success(true)
            .message("Inventory fetched successfully")
            .build();
    }

    @PostMapping("chance-stock")
    public ResponseEntity<String> chanceStock(@RequestBody StockChanceRequest request)
    {
        try{
            inventoryService.chanceStock(request);
            return ResponseEntity.ok("chance product success");
        }catch(Exception error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }
}
