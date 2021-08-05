package ru.goodex.service.entity.post;

import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class PostDTO {

    private UUID id;
    private String description;
    private String image;
    private int rating;
    private UUID profileId;
}
