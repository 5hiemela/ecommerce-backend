package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    // Injecting both services now
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Updated POST method to attach the full category data
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // 1. Check if a category was passed in the request body
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        // 2. Look up the full category from the database using its ID
        Optional<Category> fullCategory = categoryService.getCategoryById(product.getCategory().getId());

        // 3. If the category doesn't exist, return a 400 Bad Request
        if (fullCategory.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // 4. Attach the complete Category object (with name/description) to the product
        product.setCategory(fullCategory.get());

        // 5. Save the product and return the result
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
}