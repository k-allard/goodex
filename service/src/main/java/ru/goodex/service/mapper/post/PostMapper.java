package ru.goodex.service.mapper.post;

import ru.goodex.service.entity.post.Post;
import ru.goodex.service.entity.post.PostDTO;

import java.util.List;
import java.util.UUID;

public interface PostMapper {
    PostDTO convertFromEntity(Post posts);
    Post convertFromDto(PostDTO postDTO, UUID profileId);
}
