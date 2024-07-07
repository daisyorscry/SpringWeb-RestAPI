package com.restapi.app.models.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.restapi.app.models.entitiies.Product;


@Repository
public interface ProducsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>
{

}