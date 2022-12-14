package com.codestates.BocamDogam.post.controller;

import com.codestates.BocamDogam.dto.MultiResponseDto;
import com.codestates.BocamDogam.like.post_like.PostLikeService;
import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.member.service.MemberService;
import com.codestates.BocamDogam.post.dto.PostDto;
import com.codestates.BocamDogam.post.entity.Post;
import com.codestates.BocamDogam.post.mapper.PostMapper;
import com.codestates.BocamDogam.post.repository.PostRepository;
import com.codestates.BocamDogam.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

import java.util.Base64;
import java.util.List;


@RestController
@RequestMapping("/main/community/board")
@Validated
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostController(PostService postService, MemberService memberService, PostRepository postRepository, PostMapper postMapper) {
        this.postService = postService;
        this.memberService = memberService;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    // 게시글 생성
    @PostMapping("/{board}/write")
    public ResponseEntity putPost(@PathVariable("board") Post.Board board,
                                   @RequestHeader(value = "Authorization") String token,
                                   @RequestBody PostDto.Post requestBody) {

        Base64.Decoder decoder = Base64.getDecoder();
        String[] splitJwt = token.split("\\.");
        String payload = new String(decoder.decode(splitJwt[1]
                        .replace("-", "+")
                        .replace ("_", "/")));

        String email = new String(payload.substring(payload.indexOf("email") + 8, payload.indexOf("com")+3));

        Member member = memberService.findMemberByEmail(email);

        Post post = postMapper.postPostToPost(requestBody);
        post.setMember(member);
        post.setBoard(board);

        Post response = postService.createPost(post);

        return new ResponseEntity<>(postMapper.postToPostResponse(response),
                HttpStatus.CREATED);
    }

    // 게시글 수정
    @PatchMapping("/{board}/{post-id}")
    public ResponseEntity patchPost(@PathVariable("board") Post.Board board,
                                    @RequestHeader(value = "Authorization") String token,
                                    @PathVariable("post-id") @Positive Long postId,
                                    @RequestBody PostDto.Patch requestBody) {
        Post post = postService.findPost(postId);
        Long writerId = post.getMember().getMemberId();

        memberService.verifyWriterMember(token, writerId);

        requestBody.setPostId(postId);
        Post patchedPost = postService.updatePost(postMapper.postPatchToPost(requestBody));

        return new ResponseEntity<>(postMapper.postToPostResponse(patchedPost),
                HttpStatus.CREATED);
    }

    // 게시글 개별 조회
    @GetMapping("/{board}/{post-id}")
    public ResponseEntity getPost(@PathVariable("board") Post.Board board,
                                  @PathVariable("post-id") @Positive Long postId) {
        Post post = postService.findPost(postId);
        return new ResponseEntity<>(postMapper.postToPostResponse(post), HttpStatus.OK);
    }

    // 인기글 조회
    @GetMapping("/{board}/liked")
    public ResponseEntity getLikedPosts(@PathVariable("board") Post.Board board) {
        Page<Post> result = postService.findLikedPosts(board.toString());
        List<Post> response = result.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(postMapper.PostResponseToPostResponses(response), result), HttpStatus.OK);
    }
    // 게시글 전체 조회
    @GetMapping("/{board}")
    public ResponseEntity getPosts(@PathVariable("board") Post.Board board,
                                   @Positive @RequestParam int page,
                                   @Positive @RequestParam int size) {
        Page<Post> result = postService.findPosts(page - 1, size, board.toString());
        List<Post> response = result.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(postMapper.PostResponseToPostResponses(response),
                        result),
                HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/{board}/{post-id}")
    public ResponseEntity deletePost(@PathVariable("board") Post.Board board,
                                    @PathVariable("post-id") @Positive Long postId,
                                    @RequestHeader(value = "Authorization") String token) {
        memberService.verifyWriterMember(token, postService.findVerifiedPost(postId).getMember().getMemberId());

        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

