package com.practice.miniProject.cart.dto;

import com.practice.miniProject.members.entity.Member;
import com.practice.miniProject.products.entity.Product;
import lombok.*;

import javax.validation.constraints.Positive;

public class CartDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDto {
        @Positive
        private long memberId;
        @Positive
        private long productId;
        @Positive
        private long quantity;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDto {
        private long cartId;
        private long quantity;
        private String username;
        private String productName;
        private long price;

        public void setMember(Member member) {
            this.username = member.getUsername();
        }

        public void setProduct(Product product) {
            this.productName = product.getProductName();
            this.price = product.getPrice();
        }
    }
}
