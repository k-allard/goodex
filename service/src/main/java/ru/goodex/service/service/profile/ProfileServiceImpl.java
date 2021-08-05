package ru.goodex.service.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.entity.profile.Profile;
import org.springframework.stereotype.Service;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.exceptions.UserNotFoundException;
import ru.goodex.service.mapper.post.PostMapper;
import ru.goodex.service.mapper.profile.ProfileMapper;
import ru.goodex.service.repository.profile.ProfileRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final PostMapper postMapper;
    private final KafkaTemplate<Long, ProfileDTO> kafkaTemplate;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper, PostMapper postMapper, KafkaTemplate kafkaTemplate) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.postMapper = postMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public boolean createProfile(ProfileDTO profileCreationDTO) {
        Profile profile = profileRepository.findById(profileCreationDTO.getId()).orElse(null);
        if (profile == null) {
            profileRepository.save(profileMapper.convertFromDTO(profileCreationDTO));
            kafkaTemplate.send("profile", profileCreationDTO);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ProfileDTO changeProfile(ProfileDTO profileDTO) throws UserNotFoundException{
        Profile profile = profileRepository.findById(profileDTO.getId()).orElseThrow(()-> new UserNotFoundException("User with this id does not exist"));
        profile = profileMapper.convertFromDTO(profileDTO);
        profileRepository.save(profile);
        return profileMapper.convertFromEntity(profile);
    }

    @Override
    public List<PostDTO> findAllPosts(UUID userId) throws UserNotFoundException {
        Profile profile = profileRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with this id does not exist"));
        return profile.getPosts().stream().map(postMapper::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ProfileDTO> findAllFriends(UUID userId) throws UserNotFoundException {
        Profile profile = profileRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with this id does not exist"));
        return profile.getFriends().stream().map(profileMapper::convertFromEntity).collect(Collectors.toList());
    }
}

