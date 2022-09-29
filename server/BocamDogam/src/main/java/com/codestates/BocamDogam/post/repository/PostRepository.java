package com.codestates.BocamDogam.post.repository;

import com.codestates.BocamDogam.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
