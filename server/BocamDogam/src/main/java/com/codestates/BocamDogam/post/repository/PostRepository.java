package com.codestates.BocamDogam.post.repository;

import com.codestates.BocamDogam.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM POST WHERE BOARD = :board ORDER BY LIKE_COUNT DESC, CREATED_DATE DESC", nativeQuery = true)
    Page<Post> findByBoard(String board, Pageable pageable);
    @Query(value = "SELECT * FROM POST ORDER BY LIKE_COUNT DESC, CREATED_DATE DESC", nativeQuery = true)
    Page<Post> findAllOrderByLikeCountAndCreatedDate(Pageable pageable);
}
