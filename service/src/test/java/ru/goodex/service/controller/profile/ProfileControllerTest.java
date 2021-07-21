package ru.goodex.service.controller.profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.method.P;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.exceptions.UserNotFoundException;
import ru.goodex.service.service.profile.ProfileServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class ProfileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProfileServiceImpl service;

    private ProfileDTO profileDTO;

    @BeforeEach
    private void init() {

    }

    @Test
    public void tryCreateNewProfile_successExpected() throws Exception {
        profileDTO = new ProfileDTO();
        profileDTO.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8"));
        profileDTO.setFirstName("Alex");
        profileDTO.setSecondName("Blinov");
        profileDTO.setEmail("12@mail.ru");
        profileDTO.setUsername("blomblom");
        profileDTO.setImage("");
        profileDTO.setFriends(List.of());
        profileDTO.setPosts(List.of());

        when(service.createProfile(profileDTO)).thenReturn(true);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/profiles")
                .content(objectMapper.writeValueAsString(profileDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void tryGetAllFriend_successExpected() throws Exception, UserNotFoundException {
        ProfileDTO friend = new ProfileDTO();
        friend.setId(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f1"));
        List<ProfileDTO> friendsList = List.of(friend);

        when(service.findAllFriends(UUID.fromString("f99a003f-bf15-46e3-8674-7c7e2da6b8f8")))
                .thenReturn(friendsList);

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8081/profiles/{id}/friends","f99a003f-bf15-46e3-8674-7c7e2da6b8f8")

                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());

    }
}