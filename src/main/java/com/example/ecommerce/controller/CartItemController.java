package com.example.ecommerce.controller;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    // Endpoint: POST /api/cart/add?userId=1&productId=2&quantity=3
    @PostMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestParam Long userId,
                                                  @RequestParam Long productId,
                                                  @RequestParam int quantity) {
        CartItem cartItem = cartItemService.addItemToCart(userId, productId, quantity);
        return ResponseEntity.ok(cartItem);
    }

    // Endpoint: GET /api/cart?userId=1
    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(@RequestParam Long userId) {
        List<CartItem> cart = cartItemService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    // Endpoint: DELETE /api/cart/items/5
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return ResponseEntity.ok("Item removed from cart successfully.");
    }
}