package com.example.ecommerce.config;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    // Spring automatically injects our repositories here
    public DataInitializer(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only seed data if the database is currently empty
        if (categoryRepository.count() == 0 && productRepository.count() == 0) {
            System.out.println("Database is empty. Seeding sample catalog data...");

            // 1. Create and save base Categories
            Category electronics = new Category("Electronics", "Gadgets, computer parts, and accessories");
            Category apparel = new Category("Apparel", "Clothing, shoes, and wearable accessories");

            Category savedElectronics = categoryRepository.save(electronics);
            Category savedApparel = categoryRepository.save(apparel);

            // 2. Create and save Products linked to those specific categories
            Product keyboard = new Product(
                    "Mechanical Keyboard",
                    "Wireless RGB mechanical keyboard with blue switches",
                    89.99,
                    45,
                    savedElectronics
            );

            Product mouse = new Product(
                    "Gaming Mouse",
                    "Ergonomic 16000 DPI wireless gaming mouse",
                    49.99,
                    60,
                    savedElectronics
            );

            Product hoodie = new Product(
                    "Developer Hoodie",
                    "Ultra-soft cotton hoodie featuring a clean code print",
                    39.99,
                    25,
                    savedApparel
            );

            productRepository.save(keyboard);
            productRepository.save(mouse);
            productRepository.save(hoodie);

            System.out.println("Sample catalog data successfully seeded!");
        } else {
            System.out.println("Database already contains data. Skipping seeding phase.");
        }
    }
}