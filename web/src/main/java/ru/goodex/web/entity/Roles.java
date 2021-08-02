package ru.goodex.web.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


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
