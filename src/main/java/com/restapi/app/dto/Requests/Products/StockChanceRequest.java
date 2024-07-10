package com.restapi.app.dto.Requests.Products;

import com.restapi.app.models.entitiies.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockChanceRequest 
{
    private Long productId;
    
    private String chanceType;

    private Integer quantity;

    private InventoryStatus status;

}
