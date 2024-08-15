package com.practice.miniProject.products.mapper;

import com.practice.miniProject.products.dto.ProductDto;
import com.practice.miniProject.products.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "productId", ignore = true)
    Product productPostToProduct(ProductDto.PostDto requestBody);
    Product productPutToProduct(ProductDto.PutDto requestBody);
    ProductDto.ResponseDto productToResponseProduct(Product product);
}
