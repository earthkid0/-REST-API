package com.practice.miniProject.comment.repository;

import com.practice.miniProject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
