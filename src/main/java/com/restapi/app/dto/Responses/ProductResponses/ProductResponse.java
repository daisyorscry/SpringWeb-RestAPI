package com.restapi.app.dto.Responses.ProductResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    
    private String productName;
    private String productDesc;
    private String createdBy;
    private String createdAt;
    private String updatedBy;
    private String updatedAt;
    

}
