package ru.goodex.service.entity.profile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.goodex.service.entity.post.Post;

import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
public class Profile {

    private UUID id;
    private String firstName;
    private String secondName;
    private String image;
    private String username;
    private String email;
    private List<Profile> friends;
    private List<Post> posts;
}
