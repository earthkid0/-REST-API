package com.practice.miniProject.products.controller;

import com.practice.miniProject.pagination.MultiResponseDto;
import com.practice.miniProject.products.dto.ProductDto;
import com.practice.miniProject.products.entity.Product;
import com.practice.miniProject.products.mapper.ProductMapper;
import com.practice.miniProject.products.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

//    @GetMapping("/create")
//    public String CreateProductForm(Model model) {
//        model.addAttribute("product", new ProductDto.PostDto());
//
//        return "products/create-product";
//    }

    @PostMapping
    public ResponseEntity PostProduct(@Valid @RequestBody ProductDto.PostDto postDto) {
        Product product = mapper.productPostToProduct(postDto);
        productService.createProduct(product);
        ProductDto.ResponseDto response = mapper.productToResponseProduct(product);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping("/update/{product_id}")
//    private String UpdateProductForm(@PathVariable("product_id") long productId, Model model) {
//        Product product = productService.getProduct(productId);
//        ProductDto.responseDto response = mapper.productToResponseProduct(product);
//
//        model.addAttribute("product", response);
//
//        return "products/update-product";
//    }

    @PutMapping("/{product_id}")
    public ResponseEntity PutProduct(@PathVariable("product_id") long productId,
                                     @Valid ProductDto.PutDto putDto) {
        Product product = mapper.productPutToProduct(putDto);
        product.setProductId(productId);
        Product updateProduct = productService.updateProduct(product);
        ProductDto.ResponseDto response = mapper.productToResponseProduct(updateProduct);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity GetProduct(@PathVariable("product_id") long productId) {
        Product product = productService.getProduct(productId);
        ProductDto.ResponseDto response = mapper.productToResponseProduct(product);

//        model.addAttribute("product", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity GetProducts(@Positive @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 15, Sort.by("CreatedAt").descending());

        Page<Product> products = productService.getProducts(pageable);
        List<ProductDto.ResponseDto> response = products.stream()
                        .map(product -> mapper.productToResponseProduct(product))
                .collect(Collectors.toList());

//        model.addAttribute("products", response);

        return new ResponseEntity<>(new MultiResponseDto<>(response, products), HttpStatus.OK);
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity deleteProduct(@PathVariable("product_id") long productId) {
        productService.deleteProduct(productId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
