package ru.goodex.service.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import ru.goodex.service.entity.profile.Profile;
import ru.goodex.service.entity.profile.ProfileCreationDTO;
import org.springframework.stereotype.Service;
import ru.goodex.service.exceptions.UserNotFoundException;
import ru.goodex.service.mapper.profile.ProfileMapper;
import ru.goodex.service.repository.profile.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public boolean createProfile(ProfileCreationDTO profileCreationDTO) {
        Profile profile = profileRepository.findById(profileCreationDTO.getId()).orElse(null);
        if (profile == null) {
            profileRepository.save(profileMapper.convertFromCreationDTO(profileCreationDTO));
            return true;
        } else {
            return false;
        }
    }
}
