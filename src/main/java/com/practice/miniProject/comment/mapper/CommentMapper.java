package com.practice.miniProject.comment.mapper;

import com.practice.miniProject.comment.dto.CommentDto;
import com.practice.miniProject.comment.entity.Comment;
import com.practice.miniProject.members.entity.Member;
import com.practice.miniProject.posts.entity.Post;
import com.practice.miniProject.products.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    default Comment commentPostToComment(CommentDto.PostDto postDto) {
        Comment comment = new Comment();
        comment.setContent(postDto.getContent());

        Member member = new Member();
        member.setMemberId(postDto.getMemberId());
        comment.setMember(member);

        if (postDto.getPostId() != null) {
            Post post = new Post();
            post.setPostId(postDto.getPostId());
            comment.setPost(post);
        }

        if (postDto.getProductId() != null) {
            Product product = new Product();
            product.setProductId(postDto.getProductId());
            comment.setProduct(product);
        }

        return comment;
    }

    Comment commentPutToComment(CommentDto.PutDto requestBody);

    default CommentDto.ResponseDto commentToCommentResponse(Comment comment) {
        CommentDto.ResponseDto response = new CommentDto.ResponseDto();

        response.setCommentId(comment.getCommentId());
        response.setMember(comment.getMember());
        response.setContent(comment.getContent());
        response.setCreatedAt(comment.getCreatedAt());
        response.setModifiedAt(comment.getModifiedAt());

        return response;
    }
}
