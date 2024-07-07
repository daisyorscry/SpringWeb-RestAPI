package com.restapi.app.models.Requests.Products;

import lombok.Data;

@Data

public class UpdateProductRequest 
{
    private String product_name;

    private String product_desc;
}