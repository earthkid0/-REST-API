package com.practice.miniProject.comment.service;

import com.practice.miniProject.comment.entity.Comment;
import com.practice.miniProject.comment.repository.CommentRepository;
import com.practice.miniProject.exception.BusinessLogicException;
import com.practice.miniProject.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(@RequestBody Comment comment) {

        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getCommentId());

        Optional.ofNullable(comment.getContent())
                .ifPresent(content -> findComment.setContent(content));

        return commentRepository.save(findComment);
    }

//    public Comment getComment(long commentId) {
//        Comment findComment = findVerifiedComment(commentId);
//
//        return findComment;
//    }
//
//    public List<Comment> getComments() {
//
//        return commentRepository.findAll();
//    }

    public void deleteComment(long commentId) {

        commentRepository.deleteById(commentId);
    }

    public Comment findVerifiedComment(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        Comment findComment = optionalComment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        return findComment;
    }
}
