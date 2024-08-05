package com.example.template;

import labshopcontracttest.domain.Inventory;
import labshopcontracttest.domain.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestDataConfig {

    @Bean
    public CommandLineRunner initData(InventoryRepository repository) {
        return args -> {
            Inventory inventory = new Inventory();
            inventory.setId(1L);
            inventory.setStock(10);
            repository.save(inventory);
        };
    }
}
