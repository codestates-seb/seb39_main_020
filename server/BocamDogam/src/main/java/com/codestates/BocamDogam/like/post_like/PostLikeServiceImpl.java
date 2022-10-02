package com.codestates.BocamDogam.like.post_like;

import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.post.entity.Post;
import com.codestates.BocamDogam.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;

    public PostLikeServiceImpl(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    public boolean verifyInitialLikeByMemberId(Member member, Post post) {
        PostLike findMemberId = postLikeRepository.findByMemberIdAndPostId(member.getMemberId(), post.getPostId());
        if (findMemberId == null) {
            return true;
        }
        return false;
    }

    public PostLike clickLike(Member member, Post post) {
        if (verifyInitialLikeByMemberId(member, post) == true) {
            PostLike postLike = new PostLike();
            postLike.setMember(member);
            postLike.setPost(post);
            postLike.set_liked(true);
            return postLikeRepository.save(postLike);

        } else {
            PostLike postLike = postLikeRepository.findByMemberIdAndPostId(member.getMemberId(), post.getPostId());
            postLike.set_liked(!postLike.is_liked());
            return postLikeRepository.save(postLike);
        }
    }

    public int findPostLikes(Long postId) {
        List<PostLike> postLikes = postLikeRepository.findByPostId(postId);
        int totalLikes = Math.toIntExact(postLikes.stream()
                .filter(postLike -> postLike.is_liked() == true)
                .count());
        return totalLikes;
    }
}
