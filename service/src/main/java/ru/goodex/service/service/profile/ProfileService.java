package ru.goodex.service.service.profile;

import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.exceptions.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProfileService {

    boolean createProfile(ProfileDTO profileCreationDTO);

    ProfileDTO changeProfile(ProfileDTO profileDTO) throws UserNotFoundException;

    List<PostDTO> findAllPosts(UUID userId) throws UserNotFoundException;

    List<ProfileDTO> findAllFriends(UUID userId) throws UserNotFoundException;
}

