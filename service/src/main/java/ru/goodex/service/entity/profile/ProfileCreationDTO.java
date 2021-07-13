package ru.goodex.service.entity.profile;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProfileCreationDTO {

    private UUID id;
    private String secondName;
    private String firstName;
    private String image;
    private List<UUID> friends;

}
