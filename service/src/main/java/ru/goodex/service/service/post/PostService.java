package ru.goodex.service.service.post;

import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.entity.profile.Profile;

import java.util.UUID;

public interface PostService {
    boolean createPost(PostDTO postDTO, UUID profileID);
}
