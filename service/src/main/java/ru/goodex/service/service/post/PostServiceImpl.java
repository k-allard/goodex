package ru.goodex.service.service.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.entity.profile.Profile;
import ru.goodex.service.mapper.post.PostMapper;
import ru.goodex.service.repository.post.PostRepository;
import ru.goodex.service.repository.profile.ProfileRepository;

import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    @Override
    public boolean createPost(PostDTO postDTO, UUID profileId) {
        if(postDTO != null) {
            postRepository.save(postMapper.convertFromDto(postDTO, profileId));
            return true;
        }
        return false;
    }
}
