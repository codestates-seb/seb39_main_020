package com.codestates.BocamDogam.post.mapper;

import com.codestates.BocamDogam.post.dto.PostDto;
import com.codestates.BocamDogam.post.entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T15:01:52+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post postPostToPost(PostDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Post post = new Post();

        post.setTitle( requestBody.getTitle() );
        post.setContent( requestBody.getContent() );

        return post;
    }

    @Override
    public Post postPatchToPost(PostDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Post post = new Post();

        post.setPostId( requestBody.getPostId() );
        post.setBoard( requestBody.getBoard() );
        post.setTitle( requestBody.getTitle() );
        post.setContent( requestBody.getContent() );

        return post;
    }

    @Override
    public PostDto.Response postToPostResponse(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto.Response response = new PostDto.Response();

        response.setPostId( post.getPostId() );
        response.setMember( post.getMember() );
        response.setBoard( post.getBoard() );
        response.setTitle( post.getTitle() );
        response.setContent( post.getContent() );
        response.setView( post.getView() );
        response.setLikeCount( post.getLikeCount() );
        response.setReported( post.isReported() );
        response.setBlock( post.isBlock() );
        response.setCreatedDate( post.getCreatedDate() );
        response.setModifiedDate( post.getModifiedDate() );

        return response;
    }

    @Override
    public List<PostDto.Response> PostResponseToPostResponses(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostDto.Response> list = new ArrayList<PostDto.Response>( posts.size() );
        for ( Post post : posts ) {
            list.add( postToPostResponse( post ) );
        }

        return list;
    }
}
