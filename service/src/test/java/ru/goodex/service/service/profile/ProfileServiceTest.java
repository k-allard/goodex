package ru.goodex.service.service.profile;

import org.junit.jupiter.api.BeforeEach;
import ru.goodex.service.entity.profile.Profile;
import ru.goodex.service.entity.profile.ProfileCreationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.goodex.service.mapper.profile.ProfileMapper;
import ru.goodex.service.repository.profile.ProfileRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ProfileServiceTest {

    @MockBean
    private ProfileRepository profileRepository;
    @MockBean
    private ProfileMapper profileMapper;

    private ProfileService profileService;
    private Profile profile;
    private ProfileCreationDTO profileCreationDTO;

    @BeforeEach
    public void preparationDataForTests(){
        profileService = new ProfileServiceImpl(profileRepository, profileMapper);
        profile = new Profile();
        profileCreationDTO = new ProfileCreationDTO();
        profileCreationDTO.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));
    }


    @Test
    public void trySaveNewProfile_successfulSaveExpected(){
        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(Optional.empty());

        boolean saved = profileService.createProfile(profileCreationDTO);
        assertTrue(saved);
    }

    @Test
    public void trySaveNewProfile_failureSaveExpected(){
        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(Optional.of(profile));
        boolean saved = profileService.createProfile(profileCreationDTO);
        assertFalse(saved);
    }


}
