package ru.goodex.web.entity.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

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