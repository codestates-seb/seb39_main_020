package com.codestates.BocamDogam.comment.mapper;

import com.codestates.BocamDogam.comment.dto.CommentDto;
import com.codestates.BocamDogam.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    Comment commentPostToComment(CommentDto.Post requestBody);

    Comment commentPatchToComment(CommentDto.Patch requestBody);

    CommentDto.Response commentToCommentResponse(Comment comment);

    List<CommentDto.Response> commentResponseToCommentResponses(List<Comment> answers);

}
