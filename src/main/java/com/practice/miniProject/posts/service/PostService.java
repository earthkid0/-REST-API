package com.practice.miniProject.posts.service;

import com.practice.miniProject.exception.BusinessLogicException;
import com.practice.miniProject.exception.ExceptionCode;
import com.practice.miniProject.posts.entity.Post;
import com.practice.miniProject.posts.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

   public Post createPost(@RequestBody Post post) {

        return postRepository.save(post);
   }

   public Post updatePost(Post post) {
        Post findPost = findVerifiedPost(post.getPostId());

        Optional.ofNullable(post.getTitle())
                .ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent())
                .ifPresent(content -> findPost.setContent(content));
        Optional.ofNullable(post.getCategory())
                .ifPresent(category -> findPost.setCategory(category));

        return postRepository.save(findPost);
   }

   public Post getPost(long postId) {
        Post findPost = findVerifiedPost(postId);

        return findPost;
   }

   public List<Post> getPosts() {
        return postRepository.findAll();
   }

   public void deletePost(long postId) {

        postRepository.deleteById(postId);
   }

   public Post findVerifiedPost(long postId) {
       Optional<Post> optionalPost = postRepository.findById(postId);

       Post findPost = optionalPost.orElseThrow(() ->
               new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));

        return findPost;
   }
}
