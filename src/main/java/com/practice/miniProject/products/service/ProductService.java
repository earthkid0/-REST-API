package com.practice.miniProject.products.service;

import com.practice.miniProject.exception.BusinessLogicException;
import com.practice.miniProject.exception.ExceptionCode;
import com.practice.miniProject.products.entity.Product;
import com.practice.miniProject.products.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(@RequestBody Product product) {

        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        Product findProduct = findVerifiedProduct(product.getProductId());

        Optional.ofNullable(product.getPrice())
                .ifPresent(price -> findProduct.setPrice(price));
        Optional.ofNullable(product.isSoldOut())
                .ifPresent(soldOut -> product.setSoldOut(soldOut));

        return productRepository.save(findProduct);
    }

    public Product getProduct(long productId) {
        Product findProduct = findVerifiedProduct(productId);

        return findProduct;
    }

    public Page<Product> getProducts(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    public void deleteProduct(long productId) {

        productRepository.deleteById(productId);
    }

    public Product findVerifiedProduct(long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        Product findProduct = optionalProduct.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.PRODUCT_NOT_FOUND));

        return findProduct;
    }
}
