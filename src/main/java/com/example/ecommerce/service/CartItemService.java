package com.example.ecommerce.service;

import com.example.ecommerce.exception.InsufficientStockException;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartItemService(CartItemRepository cartItemRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // Add an item to a user's cart or increment quantity if it already exists
    public CartItem addItemToCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        // Business rule: Enforce live inventory check before allowing add-to-cart
        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient inventory available for product: " + product.getName());
        }

        // Check if the user already has this specific item in their cart
        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        if (existingCartItem.isPresent()) {
            CartItem itemToUpdate = existingCartItem.get();
            int newQuantity = itemToUpdate.getQuantity() + quantity;

            if (product.getStockQuantity() < newQuantity) {
                throw new InsufficientStockException("Cannot add more items. Total exceeds available stock.");
            }

            itemToUpdate.setQuantity(newQuantity);
            return cartItemRepository.save(itemToUpdate);
        } else {
            CartItem newCartItem = new CartItem(user, product, quantity);
            return cartItemRepository.save(newCartItem);
        }
    }

    // Retrieve all cart line items belonging to a specific user
    public List<CartItem> getCartByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    // Remove an individual line item from a user's cart completely
    public void removeCartItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new ResourceNotFoundException("Cart item record not found with ID: " + cartItemId);
        }
        cartItemRepository.deleteById(cartItemId);
    }

    // Wipe out all items in a cart (used immediately after a successful checkout)
    public void clearCart(Long userId) {
        List<CartItem> userCart = cartItemRepository.findByUserId(userId);
        cartItemRepository.deleteAll(userCart);
    }
}