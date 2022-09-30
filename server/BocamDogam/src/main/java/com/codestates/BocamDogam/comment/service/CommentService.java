package com.codestates.BocamDogam.comment.service;

import com.codestates.BocamDogam.comment.entity.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {


    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    Comment findComment(Long commentId);

    Page<Comment> findComments(int page, int size, String boardName);

    void deleteComment(Long commentId);

    Comment findVerifiedComment(Long commentId);
}
