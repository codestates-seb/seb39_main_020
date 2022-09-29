package com.codestates.BocamDogam.post.service;

import com.codestates.BocamDogam.auth.userdetails.MemberDetailsService;
import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import com.codestates.BocamDogam.post.entity.Post;
import com.codestates.BocamDogam.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {

        Post findPost = findVerifiedPost(post.getPostId());

        Optional.ofNullable(post.getBoard())
                .ifPresent(board -> findPost.setBoard(board));
        Optional.ofNullable(post.getTitle())
                .ifPresent(title -> findPost.setTitle(title));
        Optional.ofNullable(post.getContent())
                .ifPresent(content -> findPost.setContent(content));

        return postRepository.save(findPost);
    }

    @Override
    public Post findPost(Long postId) {
        return findVerifiedPost(postId);
    }

    @Override
    public Page<Post> findPosts(int page, int size, String boardName) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.Direction.DESC, "board");
        if(boardName == "ALL") return postRepository.findAll(pageable);
        return postRepository.findByBoard(boardName, pageable);
    }

    @Override
    public void deletePost(Long postId) {
        Post findPost = findVerifiedPost(postId);
        postRepository.delete(findPost);
    }

    @Override
    public Post findVerifiedPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        Post findPost = post.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));

        return findPost;
    }
}

