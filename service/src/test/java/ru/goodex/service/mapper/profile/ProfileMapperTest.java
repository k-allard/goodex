package ru.goodex.service.mapper.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.goodex.service.entity.post.Post;
import ru.goodex.service.entity.profile.Profile;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.repository.post.PostRepository;
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
    @MockBean
    private PostRepository postRepository;

    private ProfileMapper profileMapper;

    private ProfileDTO profileCreationDTO;
    private Profile profile;
    private Profile friend;
    private Post post;

    @BeforeEach
    public void init() {
        profileMapper = new ProfileMapperImpl(profileRepository, postRepository);
        profileCreationDTO = new ProfileDTO();
        profileCreationDTO.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));
        profileCreationDTO.setFirstName("Alex");
        profileCreationDTO.setSecondName("Blinov");
        profileCreationDTO.setImage("imagePath");
        profileCreationDTO.setEmail("123@mail.ru");
        profileCreationDTO.setUsername("sbeefany");
        profileCreationDTO.setPosts(List.of(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1")));
        profileCreationDTO.setFriends(List.of(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1")));

        friend = new Profile();
        friend.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1"));

        post = new Post();
        post.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1"));

        profile = new Profile();
        profile.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));
        profile.setFirstName("Alex");
        profile.setSecondName("Blinov");
        profile.setImage("imagePath");
        profile.setEmail("123@mail.ru");
        profile.setUsername("sbeefany");
        profile.setPosts(List.of(post));
        profile.setFriends(List.of(friend));


    }

    @Test
    void convertFromCreationDTO_successConvertationExpected() {
        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1")))
                .thenReturn(Optional.of(friend));
        when(postRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1")))
                .thenReturn(Optional.of(post));

        Profile factProfile = profileMapper.convertFromDTO(profileCreationDTO);

        assertEquals(profile, factProfile);
    }

    @Test
    public void convertEntityToDTO_sucessConvertationExpected() {
        ProfileDTO profileDTO = profileMapper.convertFromEntity(profile);

        assertEquals(profileCreationDTO, profileDTO);
    }


}