package com.restapi.app.models.respositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.app.models.entitiies.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> 
{
}
