package ru.goodex.service.mapper.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goodex.service.entity.post.Post;
import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.repository.post.PostRepository;
import ru.goodex.service.repository.profile.ProfileRepository;

import java.util.UUID;

@Component
public class PostMapperImpl implements PostMapper {
    private final ProfileRepository profileRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostMapperImpl(ProfileRepository profileRepository, PostRepository postRepository) {
        this.profileRepository = profileRepository;
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO convertFromEntity(Post posts) {
        return null;
    }
    @Override
    public Post convertFromDto(PostDTO postDTO, UUID profileId) {
        Post post = new Post();
        post.setDescription(postDTO.getDescription());
        post.setImage(postDTO.getImage());
        post.setId(UUID.randomUUID());
        post.setRating(postDTO.getRating());
        post.setProfile(profileRepository.findProfileById(profileId));
        return post;
    }
}
