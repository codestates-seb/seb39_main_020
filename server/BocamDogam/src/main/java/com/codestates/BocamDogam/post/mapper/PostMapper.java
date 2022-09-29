package com.codestates.BocamDogam.post.mapper;

import com.codestates.BocamDogam.post.dto.PostDto;
import com.codestates.BocamDogam.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    Post postPostToPost(PostDto.Post requestBody);

    Post postPatchToPost(PostDto.Patch requestBody);

    PostDto.Response postToPostResponse(Post post);

    List<PostDto.Response> PostResponseToPostResponses(List<Post> posts);
}
