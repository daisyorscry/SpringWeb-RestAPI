package com.restapi.app.models.respositories;

import org.springframework.data.repository.CrudRepository;

import com.restapi.app.models.entitiies.Product;

public interface ProductsRepository extends CrudRepository<Product, Long>
{


}
    
