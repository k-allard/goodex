package ru.goodex.web.entity;


import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


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

