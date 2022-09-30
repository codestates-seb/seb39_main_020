package com.codestates.BocamDogam.post.service;

import com.codestates.BocamDogam.post.entity.Post;
import org.springframework.data.domain.Page;

public interface PostService {

    Post createPost(Post post);

    Post updatePost(Post post);

    Post findPost(Long postId);

    Page<Post> findPosts(int page, int size, String boardName);

    void deletePost(Long postId);

    Post findVerifiedPost(Long postId);
}
