package com.restapi.app.dto.Requests.Products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateInventoryRequest 
{
    private Long productId;
        
    private Double price;

    
}
