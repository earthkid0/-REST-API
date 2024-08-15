package com.practice.miniProject.cart.service;

import com.practice.miniProject.cart.entity.Cart;
import com.practice.miniProject.cart.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart saveCart(@RequestBody Cart cart) {

        return cartRepository.save(cart);
    }

    public void deleteCart(long cartId) {
        cartRepository.deleteById(cartId);
    }
}
