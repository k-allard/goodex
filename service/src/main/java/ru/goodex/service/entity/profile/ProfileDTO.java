package ru.goodex.service.entity.profile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
import java.util.UUID;
@Data
@Setter
@Getter
@Document(indexName = "profile")
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