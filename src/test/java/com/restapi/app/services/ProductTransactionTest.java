package com.restapi.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

import com.restapi.app.models.entitiies.ProductTransaction;
import com.restapi.app.models.respositories.ProductTransactionRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTransactionTest {
    
    @Autowired
    private ProductTransactionService productService;

    @Autowired
    private ProductTransactionRepository productTransactionRepository;

    @Test
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void testReadUncommittedIsolation() throws InterruptedException, ExecutionException {
        // Persiapkan data awal
        ProductTransaction productTransaction = new ProductTransaction();
        productTransaction.setName("Sample Product");
        productTransaction.setQuantity(100);
        productTransactionRepository.save(productTransaction);

        Long productId = productTransaction.getId();

        // Transaction A: Baca quantity
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Callable<Integer> transactionA = () -> {
            return productService.readProductQuantity(productId);
        };

        // Transaction B: Ubah quantity menjadi 120 tetapi belum di-commit
        Callable<Void> transactionB = () -> {
            productService.updateProductQuantity(productId, 120);
            return null;
        };

        Future<Integer> futureA1 = executor.submit(transactionA);
        Future<Void> futureB = executor.submit(transactionB);

        // Tunggu Transaction B selesai (belum di-commit)
        futureB.get();

        // Transaction A: Baca kembali quantity (seharusnya melihat nilai 120)
        Future<Integer> futureA2 = executor.submit(transactionA);

        // Rollback Transaction B (Simulasi)
        // Anda bisa membuat metode khusus di ProductService yang akan mengubah quantity dan melempar Exception
        productTransactionRepository.save(productTransaction); // Simulasi rollback

        // Transaction A: Baca kembali quantity setelah rollback (seharusnya melihat nilai awal 100)
        Future<Integer> futureA3 = executor.submit(transactionA);

        assertEquals(100, (int) futureA1.get());
        assertEquals(120, (int) futureA2.get());
        assertEquals(100, (int) futureA3.get());

        executor.shutdown();
    }
}
