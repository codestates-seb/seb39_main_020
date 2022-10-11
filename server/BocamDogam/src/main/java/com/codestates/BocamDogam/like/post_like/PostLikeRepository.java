package com.codestates.BocamDogam.like.post_like;

import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    @Query(value = "SELECT * FROM POST_LIKE WHERE MEMBER_ID = :member_id AND POST_ID = :post_id", nativeQuery = true)
    PostLike findByMemberIdAndPostId(@Param("member_id") Long memberId, @Param("post_id") Long postId);
    @Query(value = "SELECT * FROM POST_LIKE WHERE POST_ID = :post_id", nativeQuery = true)
    List<PostLike> findByPostId(@Param("post_id") Long postId);
}
