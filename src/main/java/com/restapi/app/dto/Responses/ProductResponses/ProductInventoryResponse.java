package com.restapi.app.dto.Responses.ProductResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInventoryResponse {
    

    private String product_name;

    private String product_desc;

    private Double price;

    private Integer stock;

    private String status;
}
