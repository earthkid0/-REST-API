package com.practice.miniProject.posts.dto;


import com.practice.miniProject.comment.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;


public class PostDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestDto { // 변수나 메소드 이름에 따라 nullPointError 가 날 수 있음
        @Positive
        private long memberId;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        @NotBlank
        private String category;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDto {
        private long postId;
        private String title;
        private String content;
        private String category;
        private List<CommentDto.ResponseDto> comments;
//        private String imageUrl;

    }
}
