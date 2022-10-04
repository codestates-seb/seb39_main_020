package com.codestates.BocamDogam.comment.service;

import com.codestates.BocamDogam.comment.entity.Comment;
import com.codestates.BocamDogam.comment.repository.CommentRepository;
import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {

        Comment findComment = findVerifiedComment(comment.getCommentId());
        Optional.ofNullable(comment.getContent())
                .ifPresent(content -> findComment.setContent(content));

        return commentRepository.save(findComment);
    }

    @Override
    public Comment findComment(Long commentId) {
        return findVerifiedComment(commentId);
    }


    @Override
    public Page<Comment> findComments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.Direction.DESC, "commentId");

        return commentRepository.findAll(pageable);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment findComment = findVerifiedComment(commentId);
        commentRepository.delete(findComment);
    }

    @Override
    public Comment findVerifiedComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        Comment findComment = comment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        return findComment;
    }
}
