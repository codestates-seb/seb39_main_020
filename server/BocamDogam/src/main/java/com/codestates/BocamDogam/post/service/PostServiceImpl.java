package com.codestates.BocamDogam.post.service;

import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.post.entity.Post;
import com.codestates.BocamDogam.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
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
    public Post updatePost(Post post, String email) {
        Post findPost = findPost(post.getPostId());
        if(email.compareTo(findPost.getMember().getEmail()) == 0) {
            Optional.ofNullable(post.getBoard())
                    .ifPresent(board -> findPost.setBoard(board));
            Optional.ofNullable(post.getTitle())
                    .ifPresent(title -> findPost.setTitle(title));
            Optional.ofNullable(post.getContent())
                    .ifPresent(content -> findPost.setContent(content));
            return postRepository.save(findPost);
        } else {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_ALLOWED);
        }
        // email == findPost.getMember().getEmail() 라고 if문을 작성했을 때 else문으로 넘어갔다. -> email값과 find~ 값을 찍었을 때 콘솔 상 보이는 유저 이메일은 동일했다.
        // .compareTo() 메서드는 두 문자열을 유니코드로 비교해 동일할 경우 이꼬르 0이라고 하여 A.compareTo(B)==0으로 if문을 수정했더니 기대했던 결과가 나왔다.
        // 문자열은 == 로 비교하면 안 되는 건지 확인이 필요하다.
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
        Pageable pageable = PageRequest.of(0, 5).withPage(0);
        if(boardName == "ALL")
            return postRepository.findAllOrderByLikeCountAndCreatedDate(pageable);
        return postRepository.findByBoard(boardName, pageable);
    }

    @Override
    public void deletePost(Long postId, String email) {
        Post findPost = findVerifiedPost(postId);
        if(email.compareTo(findPost.getMember().getEmail()) == 0) {
            postRepository.delete(findPost);
        } else {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_ALLOWED);
        }
    }

    @Override
    public Post findVerifiedPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        Post findPost = post.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));

        return findPost;
    }
}

