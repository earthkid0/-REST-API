package com.practice.miniProject.posts.mapper;

import com.practice.miniProject.posts.dto.PostDto;
import com.practice.miniProject.posts.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "member.memberId", source = "memberId")
    Post postRequestToPost(PostDto.RequestDto requestBody);

    PostDto.ResponseDto postToPostResponse(Post post);
}
