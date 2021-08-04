package ru.goodex.service.service.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goodex.service.entity.post.Post;
import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.exceptions.PostNotFoundException;
import ru.goodex.service.mapper.post.PostMapper;
import ru.goodex.service.repository.post.PostRepository;


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

    @Override
    public PostDTO editPost(PostDTO postDTO) throws PostNotFoundException {
        Post post = postRepository.findPostById(postDTO.getId());
        if(post != null) {
            post = postMapper.convertFromDto(postDTO, postDTO.getProfileId());
            postRepository.save(post);
            return postMapper.convertFromEntity(post);
        }
        throw new PostNotFoundException("Post with this id does not exist");
    }

    @Override
    public boolean deletePost(UUID uuid) throws PostNotFoundException {
        Post post = postRepository.findPostById(uuid);
        if(post != null) {
            postRepository.delete(post);
            return true;
        }
        throw new PostNotFoundException("Post with this id does not exist");
    }
}
