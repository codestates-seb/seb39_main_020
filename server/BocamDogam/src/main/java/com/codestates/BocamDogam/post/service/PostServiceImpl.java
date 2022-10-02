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
import org.springframework.transaction.annotation.Transactional;

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
        // 개별 조회시마다 조회수 1 증가
        Post post = findVerifiedPost(postId);
        post.setView(post.getView()+1);
        postRepository.save(post);
        return findVerifiedPost(postId);
    }

    @Override
    public Page<Post> findPosts(int page, int size, String boardName) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.Direction.DESC, "board", "createdDate");
        if(boardName == "ALL") return postRepository.findAll(pageable);
        return postRepository.findByBoard(boardName, pageable);
    }

    public Page<Post> findLikedPosts(String boardName) {
        Sort sort = Sort.by("like_Count", "created_Date").descending();
        Sort sort2 = Sort.by("likeCount", "createdDate").descending();
        Pageable pageable = PageRequest.of(0, 5, sort).withPage(0);
        Pageable pageable2 = PageRequest.of(0, 5, sort2).withPage(0);
        if(boardName == "ALL") return postRepository.findAll(pageable2);
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

