package ru.goodex.service.mapper.profile;

import ru.goodex.service.entity.profile.Profile;
import ru.goodex.service.entity.profile.ProfileDTO;


public interface ProfileMapper {
    Profile convertFromDTO(ProfileDTO profileDTO);

    ProfileDTO convertFromEntity(Profile profile);
}
