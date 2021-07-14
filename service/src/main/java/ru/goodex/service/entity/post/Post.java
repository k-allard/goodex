package ru.goodex.service.entity.post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.goodex.service.entity.profile.Profile;

import java.util.UUID;
@Data
@Getter
@Setter
public class Post {
    private UUID id;
    private String description;
    private String image;
    private int rating;
    private Profile profile;
}
