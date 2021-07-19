package ru.goodex.service.mapper.post;

import org.springframework.stereotype.Component;
import ru.goodex.service.entity.post.Post;
import ru.goodex.service.entity.post.PostDTO;

@Component
public class PostMapperImpl implements PostMapper {
    @Override
    public PostDTO convertFromEntity(Post posts) {
        return null;
    }
}
