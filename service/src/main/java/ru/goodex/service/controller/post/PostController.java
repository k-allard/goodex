package ru.goodex.service.controller.post;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.goodex.service.entity.post.PostDTO;
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
}
