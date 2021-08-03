package ru.goodex.service.service.profile;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.parameters.P;
import ru.goodex.service.entity.post.Post;
import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.entity.profile.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.exceptions.UserNotFoundException;
import ru.goodex.service.mapper.post.PostMapper;
import ru.goodex.service.mapper.profile.ProfileMapper;
import ru.goodex.service.repository.profile.ProfileRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ProfileServiceTest {

    @MockBean
    private ProfileRepository profileRepository;
    @MockBean
    private ProfileMapper profileMapper;
    @MockBean
    private PostMapper postMapper;
    @MockBean
    private KafkaTemplate<Long, ProfileDTO> kafkaTemplate;

    private ProfileService profileService;
    private Profile profile;
    private ProfileDTO profileDTO;


    @BeforeEach
    public void preparationDataForTests() {
        profileService = new ProfileServiceImpl(profileRepository, profileMapper, postMapper, kafkaTemplate);

        profile = new Profile();
        Profile newFriend = new Profile();
        newFriend.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1"));
        profile.setFriends(List.of(newFriend));
        Post newPost = new Post();
        newPost.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1"));
        profile.setPosts(List.of(newPost));

        profileDTO = new ProfileDTO();
        profileDTO.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));
        profileDTO.setFirstName("Alex");

    }


    @Test
    public void trySaveNewProfile_successfulSaveExpected() {


        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(Optional.empty());

        boolean saved = profileService.createProfile(profileDTO);
        assertTrue(saved);
    }

    @Test
    public void trySaveNewProfile_failureSaveExpected() {

        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(Optional.of(profile));

        boolean saved = profileService.createProfile(profileDTO);
        assertFalse(saved);
    }

    @Test
    public void tryToChangeProfile_successfulSaveExpected() throws UserNotFoundException {
        ProfileDTO expectedProfile = new ProfileDTO();

        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(Optional.of(profile));
        when(profileMapper.convertFromDTO(profileDTO))
                .thenReturn(profile);
        when(profileMapper.convertFromEntity(profile))
                .thenReturn(expectedProfile);


        ProfileDTO newProfile = profileService.changeProfile(profileDTO);
        assertEquals(expectedProfile, newProfile);
    }

    @Test
    public void tryToChangeProfile_userWithThisIdDoesNotExistFailureSaveExpected() {

        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(Optional.empty());


        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> {
                    ProfileDTO newProfile = profileService.changeProfile(profileDTO);
                });

        String expectedMessage = "User with this id does not exist";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void tryToGetAllPosts_userWithThisIdDoesNotExistFailureResultExpected() {
        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            List<PostDTO> posts = profileService.findAllPosts(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));
        });

        String expectedMessage = "User with this id does not exist";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void tryToGetAllPosts_successfulResultExpected() throws UserNotFoundException {
        PostDTO post = new PostDTO();
        post.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1"));
        List<PostDTO> postsList = List.of(post);


        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(Optional.of(profile));
        when(postMapper.convertFromEntity(profile.getPosts().get(0)))
                .thenReturn(post);

        List<PostDTO> posts = profileService.findAllPosts(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));

        assertEquals(postsList, posts);
    }

    @Test
    public void tryToGetAllFriends_userWithThisIdDoesNotExistFailureResultExpected() {
        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            List<ProfileDTO> friends = profileService.findAllFriends(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));
        });
        String expectedMessage = "User with this id does not exist";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void tryToGetAllFriends_successfulResultExpected() throws UserNotFoundException {
        ProfileDTO friend = new ProfileDTO();
        friend.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1"));
        List<ProfileDTO> friendsList = List.of(friend);


        when(profileRepository.findById(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(Optional.of(profile));
        when(profileMapper.convertFromEntity(profile.getFriends().get(0)))
                .thenReturn(friend);

        List<ProfileDTO> friends = profileService.findAllFriends(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));

        assertEquals(friendsList, friends);
    }


}
