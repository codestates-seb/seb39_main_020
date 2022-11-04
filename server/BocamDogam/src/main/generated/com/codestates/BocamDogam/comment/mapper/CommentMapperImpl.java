package com.codestates.BocamDogam.comment.mapper;

import com.codestates.BocamDogam.comment.dto.CommentDto;
import com.codestates.BocamDogam.comment.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T15:01:53+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentPostToComment(CommentDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setContent( requestBody.getContent() );

        return comment;
    }

    @Override
    public Comment commentPatchToComment(CommentDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentId( requestBody.getCommentId() );
        comment.setContent( requestBody.getContent() );

        return comment;
    }

    @Override
    public CommentDto.Response commentToCommentResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto.Response response = new CommentDto.Response();

        response.setCommentId( comment.getCommentId() );
        response.setPost( comment.getPost() );
        response.setMember( comment.getMember() );
        response.setContent( comment.getContent() );
        response.setLike_count( comment.getLike_count() );
        response.setReported( comment.isReported() );
        response.setBlock( comment.isBlock() );
        response.setCreatedDate( comment.getCreatedDate() );
        response.setModifiedDate( comment.getModifiedDate() );

        return response;
    }

    @Override
    public List<CommentDto.Response> commentResponseToCommentResponses(List<Comment> answers) {
        if ( answers == null ) {
            return null;
        }

        List<CommentDto.Response> list = new ArrayList<CommentDto.Response>( answers.size() );
        for ( Comment comment : answers ) {
            list.add( commentToCommentResponse( comment ) );
        }

        return list;
    }
}
