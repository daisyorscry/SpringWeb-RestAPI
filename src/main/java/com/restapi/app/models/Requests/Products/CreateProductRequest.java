package com.restapi.app.models.Requests.Products;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequest {
 
    @NotEmpty(message = "Product name is required")
    @Size(max = 100, message = "Product description is too long")
    private String product_name;

    @NotEmpty(message = "Product description is required")
    @Size(max = 255, message = "Product description is too long")
    private String product_desc;

    private String created_by;
    
    private String updated_by;
}
