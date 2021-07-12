package ru.goodex.goodex.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
public class Roles {
    @Id
    @Column(name = "role_id", nullable = false)
    @GeneratedValue(generator = "uid")
    @GenericGenerator(name = "uid", strategy = "guid")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
}