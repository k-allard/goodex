package ru.goodex.service.mapper.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goodex.service.entity.post.Post;
import ru.goodex.service.entity.profile.Profile;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.repository.post.PostRepository;
import ru.goodex.service.repository.profile.ProfileRepository;

import java.util.stream.Collectors;

@Component
public class ProfileMapperImpl implements ProfileMapper {

    private final ProfileRepository profileRepository;
    private final PostRepository postRepository;

    @Autowired
    public ProfileMapperImpl(ProfileRepository profileRepository, PostRepository postRepository) {
        this.profileRepository = profileRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Profile convertFromDTO(ProfileDTO profileCreationDTO) {
        Profile newProfile = new Profile();
        newProfile.setId(profileCreationDTO.getId());
        newProfile.setFirstName(profileCreationDTO.getFirstName());
        newProfile.setSecondName(profileCreationDTO.getSecondName());
        newProfile.setImage(profileCreationDTO.getImage());
        newProfile.setUsername(profileCreationDTO.getUsername());
        newProfile.setEmail(profileCreationDTO.getEmail());
        newProfile.setFriends(profileCreationDTO.getFriends().stream()
                .map(uuid -> profileRepository.findById(uuid).orElse(null)).collect(Collectors.toList())
        );
        newProfile.setPosts(profileCreationDTO.getPosts().stream()
                .map(uuid -> postRepository.findById(uuid).orElse(null)).collect(Collectors.toList())
        );
        return newProfile;
    }

    @Override
    public ProfileDTO convertFromEntity(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setFirstName(profile.getFirstName());
        profileDTO.setSecondName(profile.getSecondName());
        profileDTO.setImage(profile.getImage());
        profileDTO.setUsername(profile.getUsername());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setFriends(profile.getFriends().stream()
                .map(Profile::getId).collect(Collectors.toList())
        );
        profileDTO.setPosts(profile.getPosts().stream()
                .map(Post::getId).collect(Collectors.toList())
        );
        return profileDTO;
    }

}
