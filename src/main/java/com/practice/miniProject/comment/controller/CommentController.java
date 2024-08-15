package com.practice.miniProject.comment.controller;

import com.practice.miniProject.comment.dto.CommentDto;
import com.practice.miniProject.comment.entity.Comment;
import com.practice.miniProject.comment.mapper.CommentMapper;
import com.practice.miniProject.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper mapper;

    public CommentController(CommentService commentService, CommentMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.PostDto postDto) {
        Comment comment = mapper.commentPostToComment(postDto);
        Comment createComment = commentService.createComment(comment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{comment_id}")
    public ResponseEntity putComment(@PathVariable("comment_id") long commentId,
                                     @Valid CommentDto.PutDto putDto) {
        Comment comment = mapper.commentPutToComment(putDto);
        comment.setCommentId(commentId);
        Comment updateComment = commentService.updateComment(comment);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity deleteComment(@PathVariable("comment_id") long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
