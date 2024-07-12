package com.restapi.app.models.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.app.models.entitiies.Product;
import com.restapi.app.models.entitiies.ProductTransaction;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}