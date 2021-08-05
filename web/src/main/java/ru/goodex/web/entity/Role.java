package ru.goodex.web.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum Role {
    User("User"), Admin("Admin");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
