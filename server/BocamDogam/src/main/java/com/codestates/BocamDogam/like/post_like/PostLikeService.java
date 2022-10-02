package com.codestates.BocamDogam.like.post_like;

import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.post.entity.Post;
import org.springframework.stereotype.Service;

public interface PostLikeService {

    public boolean verifyInitialLikeByMemberId(Member member, Post post);

    public PostLike clickLike(Member member, Post post);

    public int findPostLikes(Long postId);
}