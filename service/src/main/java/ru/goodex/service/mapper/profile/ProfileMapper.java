package ru.goodex.service.mapper.profile;

import ru.goodex.service.entity.profile.Profile;
import ru.goodex.service.entity.profile.ProfileCreationDTO;

public interface ProfileMapper {
    Profile convertFromCreationDTO(ProfileCreationDTO profileCreationDTO);

}
