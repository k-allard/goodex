package ru.goodex.service.service.profile;

import ru.goodex.service.entity.profile.ProfileCreationDTO;
import ru.goodex.service.exceptions.UserNotFoundException;

public interface ProfileService {

    boolean createProfile(ProfileCreationDTO profileCreationDTO);

}
