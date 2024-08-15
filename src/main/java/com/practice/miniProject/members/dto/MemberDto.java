package com.practice.miniProject.members.dto;

import com.practice.miniProject.cart.dto.CartDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PostDto {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email
        private String email;

        @NotBlank(message = "닉네임은 필수입니다.")
        @Size(max = 10, message = "닉네임은 10글자를 넘길 수 없습니다.")
        private String username;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PutDto {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDto {
        private long memberId;
        private String email;
        private String username;
        private String password;
        private List<CartDto.ResponseDto> carts;
    }
}
