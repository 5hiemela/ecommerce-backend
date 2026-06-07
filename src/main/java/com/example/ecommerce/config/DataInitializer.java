package com.example.ecommerce.config;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DataInitializer(CategoryRepository categoryRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0 && productRepository.count() == 0 && userRepository.count() == 0) {
            System.out.println("Database is empty. Seeding sample data...");

            // Seed a test user account
            User testUser = new User("John", "Doe", "john@example.com", "password123", "CUSTOMER");
            userRepository.save(testUser);

            // Seed categories
            Category electronics = new Category("Electronics", "Gadgets, computer parts, and accessories");
            Category apparel = new Category("Apparel", "Clothing, shoes, and wearable accessories");

            Category savedElectronics = categoryRepository.save(electronics);
            Category savedApparel = categoryRepository.save(apparel);

            // Seed products
            Product keyboard = new Product("Mechanical Keyboard", "Wireless RGB mechanical keyboard", 89.99, 45, savedElectronics);
            Product mouse = new Product("Gaming Mouse", "Ergonomic wireless gaming mouse", 49.99, 60, savedElectronics);
            Product hoodie = new Product("Developer Hoodie", "Ultra-soft cotton hoodie", 39.99, 25, savedApparel);

            productRepository.save(keyboard);
            productRepository.save(mouse);
            productRepository.save(hoodie);

            System.out.println("Sample data successfully seeded!");
        } else {
            System.out.println("Database already contains data. Skipping seeding phase.");
        }
    }
}