package ru.goodex.service.mapper.post;

import ru.goodex.service.entity.post.Post;
import ru.goodex.service.entity.post.PostDTO;

public interface PostMapper {
    PostDTO convertFromEntity(Post posts);
}
