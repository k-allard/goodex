package ru.goodex.service.controller.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.exceptions.UserNotFoundException;
import ru.goodex.service.service.profile.ProfileService;

import java.util.List;
import java.util.UUID;

@Controller
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profiles")
    public ResponseEntity createProfile(@RequestBody ProfileDTO profileDTO) {
        boolean result = profileService.createProfile(profileDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/profiles/{profileId}")
    public ResponseEntity createProfile(@RequestBody ProfileDTO profileDTO, @PathVariable("profileId") UUID id) {
        ResponseEntity responseEntity;
        try {
            profileDTO.setId(id);
            ProfileDTO result = profileService.changeProfile(profileDTO);
            responseEntity = ResponseEntity.ok(result);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/profiles/{profileId}/friends")
    public ResponseEntity findAllFriends(@PathVariable("profileId") UUID id) {
        ResponseEntity responseEntity;
        try {
            List<ProfileDTO> result = profileService.findAllFriends(id);
            responseEntity = ResponseEntity.ok(result);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/profiles/{profileId}/posts")
    public ResponseEntity findAllPosts(@PathVariable("profileId") UUID id) {
        ResponseEntity responseEntity;
        try {
            List<PostDTO> result = profileService.findAllPosts(id);
            responseEntity = ResponseEntity.ok(result);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
