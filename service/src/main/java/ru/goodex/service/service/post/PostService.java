package ru.goodex.service.service.post;

import java.util.UUID;
import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.exceptions.PostNotFoundException;


public interface PostService {
    boolean createPost(PostDTO postDTO, UUID profileID);

    boolean deletePost(UUID postId) throws PostNotFoundException;

    PostDTO editPost(PostDTO postDTO) throws PostNotFoundException;
}
