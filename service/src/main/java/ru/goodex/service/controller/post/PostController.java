package ru.goodex.service.controller.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.goodex.service.entity.post.PostDTO;
import ru.goodex.service.exceptions.PostNotFoundException;
import ru.goodex.service.service.post.PostService;

import java.util.UUID;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/profiles/{profileId}/create")
    public ResponseEntity createProfile(@RequestBody PostDTO postDTO, @PathVariable("profileId") UUID id) {
        ResponseEntity responseEntity;
        responseEntity = ResponseEntity.ok(postService.createPost(postDTO, id));
        return responseEntity;
    }

    @PutMapping("profiles/{profileId}/{postId}")
    public ResponseEntity editPost(@RequestBody PostDTO postDTO,
                                   @PathVariable("profileId") UUID profileId,
                                   @PathVariable("postId") UUID postID) {
        ResponseEntity responseEntity;
        try {
            postDTO.setProfileId(profileId);
            postDTO.setId(postID);
            postDTO = postService.editPost(postDTO);
            responseEntity = ResponseEntity.ok(postDTO);
        } catch (PostNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
