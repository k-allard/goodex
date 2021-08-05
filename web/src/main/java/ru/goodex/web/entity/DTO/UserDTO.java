package ru.goodex.web.entity.DTO;

import liquibase.pro.packaged.S;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDTO {

    private UUID id;
    private String email;
    private String username;
    private String token;
    private List<String> roles;
    private Boolean active;

    public UserDTO(UUID id, String email, String username, String token, List<String> roles, Boolean active) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.roles = roles;
        this.email = email;
        this.active = active;
    }


}
