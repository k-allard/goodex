package ru.goodex.web.entity;

import liquibase.pro.packaged.C;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    @Column(name = "role_id", nullable = false)
    @GeneratedValue(generator = "uid")
    @GenericGenerator(name = "uid", strategy = "guid")
    private UUID id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}