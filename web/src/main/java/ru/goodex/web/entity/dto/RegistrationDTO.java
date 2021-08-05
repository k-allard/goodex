package ru.goodex.web.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {

    private String email;
    private String username;
    private String password;
    private String firstName;
    private String secondName;

}
