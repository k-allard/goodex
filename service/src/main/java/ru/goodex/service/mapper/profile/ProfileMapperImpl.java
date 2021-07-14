package ru.goodex.service.mapper.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goodex.service.entity.profile.Profile;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.repository.profile.ProfileRepository;

import java.util.stream.Collectors;

@Component
public class ProfileMapperImpl implements ProfileMapper {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileMapperImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile convertFromDTO(ProfileDTO profileCreationDTO) {
        Profile newProfile = new Profile();
        newProfile.setId(profileCreationDTO.getId());
        newProfile.setFirstName(profileCreationDTO.getFirstName());
        newProfile.setSecondName(profileCreationDTO.getSecondName());
        newProfile.setImage(profileCreationDTO.getImage());
        newProfile.setFriends(profileCreationDTO.getFriends().stream()
                .map(uuid -> profileRepository.findById(uuid).orElse(null)).collect(Collectors.toList())
        );
        return newProfile;
    }

    @Override
    public ProfileDTO convertFromEntity(Profile profile) {
        return null;
    }

}
