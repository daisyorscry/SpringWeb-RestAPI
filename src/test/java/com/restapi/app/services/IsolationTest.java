package com.restapi.app.services;

import com.restapi.app.dto.Requests.Products.StockChanceRequest;
import com.restapi.app.models.entitiies.InventoryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;



@SpringBootTest
public class IsolationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private InventoryService inventoryService;

    @Test
    public void testConcurrency()
    {

        for (int i = 0; i < 10; i++) {
                try {
                    StockChanceRequest request = new StockChanceRequest();
                    request.setInventoryId(64L); // Ganti dengan id inventory yang valid
                    request.setQuantity(1); // Menambah 10 stock
                    request.setChanceType("ADD");
                    request.setStatus(InventoryStatus.AVAILABLE);

                    inventoryService.chanceStock(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
    }

    @Test
    public void testConcurrency2()
    {

        for (int i = 0; i < 10; i++) {
                try {
                    StockChanceRequest request = new StockChanceRequest();
                    request.setInventoryId(64L); // Ganti dengan id inventory yang valid
                    request.setQuantity(1); // Menambah 10 stock
                    request.setChanceType("ADD");
                    request.setStatus(InventoryStatus.AVAILABLE);

                    inventoryService.chanceStock(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
    }

    
}
