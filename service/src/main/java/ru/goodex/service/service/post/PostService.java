package ru.goodex.service.service.post;

import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.exceptions.PostNotFoundException;

import java.util.UUID;

public interface PostService {
    boolean createPost(PostDTO postDTO, UUID profileID);
    boolean deletePost(UUID postId) throws PostNotFoundException;
    PostDTO editPost(PostDTO postDTO) throws PostNotFoundException;
}
