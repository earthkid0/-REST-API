package com.practice.miniProject.cart.controller;

import com.practice.miniProject.cart.dto.CartDto;
import com.practice.miniProject.cart.entity.Cart;
import com.practice.miniProject.cart.mapper.CartMapper;
import com.practice.miniProject.cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Validated
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final CartMapper mapper;

    public CartController(CartService cartService, CartMapper mapper) {
        this.cartService = cartService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity saveCart(@Valid @RequestBody CartDto.PostDto postDto) {
        Cart cart = mapper.cartPostToCart(postDto);
        Cart saveCart = cartService.saveCart(cart);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{cart_id}")
    public ResponseEntity deleteCart(@PathVariable("cart_id") long cartId) {
        cartService.deleteCart(cartId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
