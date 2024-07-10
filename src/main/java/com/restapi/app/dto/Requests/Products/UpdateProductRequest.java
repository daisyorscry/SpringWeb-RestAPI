package com.restapi.app.dto.Requests.Products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRequest 
{
    private String product_name;

    private String product_desc;
}