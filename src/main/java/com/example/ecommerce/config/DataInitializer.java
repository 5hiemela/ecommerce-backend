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
            System.out.println("Database is empty. Seeding tech workspace sample data...");

            // Seed a test user account
            User testUser = new User("John", "Doe", "john@example.com", "password123", "CUSTOMER");
            userRepository.save(testUser);

            // Seed Workspace Categories
            Category peripherals = new Category("Peripherals", "High-performance keyboards, mice, and input devices");
            Category organization = new Category("Organization", "Premium mats, risers, and organizational tools");

            Category savedPeripherals = categoryRepository.save(peripherals);
            Category savedOrganization = categoryRepository.save(organization);

            // Seed Workspace Products
            Product keyboard = new Product(
                    "Mechanical Keyboard (Linear)",
                    "Hot-swappable switches with dense, sound-dampening foam layers.",
                    89.99,
                    45,
                    savedPeripherals
            );

            Product mouse = new Product(
                    "Ergonomic Vertical Mouse",
                    "Designed to sit at a natural handshake angle to reduce wrist fatigue during coding sessions.",
                    65.00,
                    60,
                    savedPeripherals
            );

            Product mat = new Product(
                    "Premium Felt Desk Mat",
                    "Eco-friendly merino wool felt protects your desk and upgrades your mouse glide.",
                    34.50,
                    100,
                    savedOrganization
            );

            Product riser = new Product(
                    "Walnut Monitor Riser",
                    "Solid hardwood construction with integrated slots for cable management.",
                    79.99,
                    25,
                    savedOrganization
            );

            Product lamp = new Product(
                    "Monitor Light Bar",
                    "Sits on top of your monitor to illuminate your desk space without creating screen glare.",
                    49.99, 30, savedOrganization
            );

            Product pad = new Product(
                    "MagSafe Charging Stand",
                    "Aluminum desktop stand that charges your phone vertically or horizontally.",
                    29.50, 40, savedOrganization
            );

            Product trackpad = new Product(
                    "Wireless Trackpad",
                    "Rechargeable touch surface with support for full multi-touch gestures.",
                    110.00, 15, savedPeripherals
            );

            productRepository.save(keyboard);
            productRepository.save(mouse);
            productRepository.save(mat);
            productRepository.save(riser);
            productRepository.save(lamp);
            productRepository.save(pad);
            productRepository.save(trackpad);

            System.out.println("Tech workspace sample data successfully seeded!");
        } else {
            System.out.println("Database already contains data. Skipping seeding phase.");
        }
    }
}