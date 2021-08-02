package ru.goodex.service.service.profile;

import java.util.List;
import java.util.UUID;
import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.exceptions.UserNotFoundException;


public interface ProfileService {

    boolean createProfile(ProfileDTO profileCreationDTO);

    ProfileDTO changeProfile(ProfileDTO profileDTO) throws UserNotFoundException;

    List<PostDTO> findAllPosts(UUID userId) throws UserNotFoundException;

    List<ProfileDTO> findAllFriends(UUID userId) throws UserNotFoundException;
}

