package com.practice.miniProject.posts.controller;

import com.practice.miniProject.posts.dto.PostDto;
import com.practice.miniProject.posts.entity.Post;
import com.practice.miniProject.posts.mapper.PostMapper;
import com.practice.miniProject.posts.service.PostService;
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

@RestController //REST API
//@Controller //thymeleaf view형태로 반환
@RequestMapping("/posts")
@Validated
public class PostController {
    private final PostService postService;
    private final PostMapper mapper;

    public PostController(PostService postService, PostMapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

//    @GetMapping("/create")
//    public String postPostForm(Model model) {
//        model.addAttribute("post", new PostDto.RequestDto());
//
//        return "posts/create-post";
//    }

//    @PostMapping
//    public String postPost(@Valid @ModelAttribute("post") PostDto.RequestDto requestDto) {
//        Post post = mapper.postRequestToPost(requestDto);
//        postService.createPost(post);
//
//        return "redirect:/posts";
//    }

    @PostMapping
    public ResponseEntity postPost(@Valid @RequestBody PostDto.RequestDto requestDto) {
        Post post = mapper.postRequestToPost(requestDto);
        Post createPost = postService.createPost(post);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/update/{post_id}")
    public ResponseEntity updatePost(@PathVariable("post_id") long postId) {
        Post post = postService.getPost(postId);
        PostDto.ResponseDto response = mapper.postToPostResponse(post);
//        model.addAttribute("post", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{post_id}")
    public ResponseEntity putPost(@PathVariable("post_id") @Positive long postId,
                                  @Valid PostDto.RequestDto requestDto) {
        Post post = mapper.postRequestToPost(requestDto);
        post.setPostId(postId);
        Post updatePost = postService.updatePost(post);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getPosts() {
        List<Post> posts = postService.getPosts();

        List<PostDto.ResponseDto> response = posts.stream()
                .map(post -> mapper.postToPostResponse(post))
                .collect(Collectors.toList());
//        model.addAttribute("posts", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
//        return "posts/post-list";
    }

    @GetMapping("/{post_id}")
    public ResponseEntity getPost(@PathVariable("post_id") long postId) {
        Post post = postService.getPost(postId);
        PostDto.ResponseDto response = mapper.postToPostResponse(post);

//        model.addAttribute("post", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity deletePost(@PathVariable("post_id") long postId) {
        postService.deletePost(postId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
