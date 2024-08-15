package com.practice.miniProject.products.dto;

import com.practice.miniProject.comment.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class ProductDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostDto {
        private String productName;
        private String info;
        private long price;
        private String category;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PutDto {
        private long productId;
        private long price;
        private boolean soldOut;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDto {
        private long productId;
        private String productName;
        private String info;
        private long price;
        private boolean soldOut;
        private List<CommentDto.ResponseDto> comments;
    }

}
