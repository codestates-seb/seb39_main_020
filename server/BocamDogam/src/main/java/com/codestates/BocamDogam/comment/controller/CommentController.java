package com.codestates.BocamDogam.comment.controller;

import com.codestates.BocamDogam.comment.dto.CommentDto;
import com.codestates.BocamDogam.comment.entity.Comment;
import com.codestates.BocamDogam.comment.mapper.CommentMapper;
import com.codestates.BocamDogam.comment.repository.CommentRepository;
import com.codestates.BocamDogam.comment.service.CommentService;
import com.codestates.BocamDogam.dto.MultiResponseDto;
import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.member.service.MemberService;
import com.codestates.BocamDogam.post.entity.Post;
import com.codestates.BocamDogam.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/main/community")
@Validated
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostService postService;
    private final MemberService memberService;

    public CommentController(CommentService commentService, CommentRepository commentRepository, CommentMapper commentMapper, PostService postService, MemberService memberService) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postService = postService;
        this.memberService = memberService;
    }

    // 댓글 등록
    @PostMapping("/{post-id}/comments")
    public ResponseEntity postComment(@PathVariable("post-id") @Positive Long postId,
                                      @RequestHeader(value = "Authorization") String token,
                                      @RequestBody CommentDto.Post requestBody) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberService.findMemberByEmail(auth.getPrincipal().toString());
        Post post = postService.findVerifiedPost(postId);

        Comment comment = commentMapper.commentPostToComment(requestBody);

        comment.setMember(member);
        comment.setPost(post);

        Comment response = commentService.createComment(comment);

        return new ResponseEntity<>(commentMapper.commentToCommentResponse(response),
                HttpStatus.CREATED);
    }

    // 댓글 수정
    @PatchMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("post-id") @Positive Long postId,
                                       @PathVariable("comment-id") @Positive Long commentId,
                                       @RequestHeader(value = "Authorization") String token,
                                       @RequestBody CommentDto.Patch requestBody) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getPrincipal().toString();

        requestBody.setCommentId(commentId);
        Comment patchedComment = commentService.updateComment(commentMapper.commentPatchToComment(requestBody), email);

        return new ResponseEntity<>(commentMapper.commentToCommentResponse(patchedComment),
                HttpStatus.CREATED);
    }

    // 게시글에 등록된 댓글 전체 조회
    @GetMapping("/{post-id}/comments")
    public ResponseEntity getComments(@PathVariable("post-id") Long questionId,
                                      @RequestParam @Positive int page,
                                      @RequestParam @Positive int size) {
        Page<Comment> pageComments  = commentService.findComments(page - 1, size);
        List<Comment> answers = pageComments.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(commentMapper.commentResponseToCommentResponses(answers),
                        pageComments),
                HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("post-id") Long postId,
                                        @PathVariable("comment-id") Long commentId,
                                        @RequestHeader("Authorization") String token) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getPrincipal().toString();
        commentService.deleteComment(commentId, email);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}