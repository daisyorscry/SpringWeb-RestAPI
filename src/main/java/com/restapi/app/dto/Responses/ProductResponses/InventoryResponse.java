package com.restapi.app.dto.Responses.ProductResponses;

import java.time.LocalDateTime;
import java.util.Date;

import com.restapi.app.models.entitiies.InventoryStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class InventoryResponse {
    private Long inventoryId;
    private Long productId;
    private String productName;
    private String productDesc;
    private Double price;
    private Integer stock;
    private InventoryStatus status;
    private String createdByUsername;
    private LocalDateTime createdAt;
}
