package ru.goodex.service.entity.profile;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
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
