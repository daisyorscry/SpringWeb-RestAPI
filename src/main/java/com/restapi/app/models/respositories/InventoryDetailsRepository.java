package com.restapi.app.models.respositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.app.models.entitiies.Inventory;
import com.restapi.app.models.entitiies.InventoryDetails;

@Repository
public interface InventoryDetailsRepository extends JpaRepository<InventoryDetails, Long> {

    Optional<InventoryDetails> findByInventory(Inventory inventory);
}