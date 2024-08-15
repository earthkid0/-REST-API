package com.practice.miniProject.products.repository;

import com.practice.miniProject.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
