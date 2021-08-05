package ru.goodex.web.entity.dto;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ProfileDTO {

    private UUID id;
    private String firstName;
    private String secondName;
    private String image;
    private String username;
    private String email;
    private List<UUID> friends;
    private List<UUID> posts;

}
