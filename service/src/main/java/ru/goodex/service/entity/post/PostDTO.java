package ru.goodex.service.entity.post;

import lombok.Getter;
import lombok.Setter;
import ru.goodex.service.entity.profile.Profile;

import java.util.UUID;

@Getter
@Setter
public class PostDTO {

    private UUID id;
    private String description;
    private String image;
    private int rating;
    private UUID profileId;
}
