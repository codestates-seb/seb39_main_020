package com.codestates.BocamDogam.like.post_like;

import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.member.service.MemberService;
import com.codestates.BocamDogam.post.entity.Post;
import com.codestates.BocamDogam.post.repository.PostRepository;
import com.codestates.BocamDogam.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
public class PostLikeController {

    private final PostLikeService postLikeService;
    private final MemberService memberService;
    private final PostService postService;
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public PostLikeController(PostLikeService postLikeService, MemberService memberService, PostService postService, PostLikeRepository postLikeRepository, PostRepository postRepository) {
        this.postLikeService = postLikeService;
        this.memberService = memberService;
        this.postService = postService;
        this.postLikeRepository = postLikeRepository;
        this.postRepository = postRepository;
    }

    @PutMapping("/main/community/like/{post-id}")
    public ResponseEntity putPostLike(@RequestHeader("Authorization") String token,
                               @PathVariable("post-id") Long postId) {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] splitJwt = token.split("\\.");
        String payload = new String(decoder.decode(splitJwt[1]
                .replace("-", "+")
                .replace ("_", "/")));
        String email = new String(payload.substring(payload.indexOf("email") + 8, payload.indexOf("com")+3));

        Member member = memberService.findMemberByEmail(email);
        Post post = postService.findVerifiedPost(postId);

        PostLike postLike = postLikeService.clickLike(member, post);

        post.setLikeCount(postLikeService.findPostLikes(postId));
        postRepository.save(post);

        return new ResponseEntity(postLike.isLiked(), HttpStatus.OK);
    }

    @GetMapping("/main/community/like/{post-id}")
    public ResponseEntity getPostLike(@PathVariable("post-id") Long postId) {
        int postLikes = postLikeService.findPostLikes(postId);

        return new ResponseEntity<>(postLikes, HttpStatus.OK);
    }
}
