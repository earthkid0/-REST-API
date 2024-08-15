package com.practice.miniProject.comment.dto;

import com.practice.miniProject.members.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostDto {
        private long memberId;
        private Long postId;
        private Long productId;
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PutDto {
        private long memberId;
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseDto {
        private long commentId;
        private long memberId;
        private String username;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public void setMember(Member member) {
            this.memberId = member.getMemberId();
            this.username = member.getUsername();
        }
    }
}
