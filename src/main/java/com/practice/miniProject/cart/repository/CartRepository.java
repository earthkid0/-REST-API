package com.practice.miniProject.cart.repository;

import com.practice.miniProject.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
