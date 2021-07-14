package ru.goodex.service.mapper.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.goodex.service.entity.profile.Profile;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.repository.profile.ProfileRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class ProfileMapperTest {

    @MockBean
    private ProfileRepository profileRepository;

    private ProfileMapper profileMapper;

    private ProfileDTO profileCreationDTO;
    private Profile profile;
    private Profile friend;

    @BeforeEach
    public void init() {
        profileMapper = new ProfileMapperImpl(profileRepository);
        profileCreationDTO = new ProfileDTO();
        profileCreationDTO.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));
        profileCreationDTO.setFirstName("Alex");
        profileCreationDTO.setSecondName("Blinov");
        profileCreationDTO.setImage("imagePath");
        profileCreationDTO.setFriends(List.of(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1")));

        friend = new Profile();
        friend.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1"));

        profile = new Profile();
        profile.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));
        profile.setFirstName("Alex");
        profile.setSecondName("Blinov");
        profile.setImage("imagePath");
        profile.setFriends(List.of(friend));


    }

    @Test
    void convertFromCreationDTO_successConvertationExpected() {
        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1")))
                .thenReturn(Optional.of((friend)));

        Profile factProfile = profileMapper.convertFromDTO(profileCreationDTO);

        assertEquals(profile, factProfile);
    }


}