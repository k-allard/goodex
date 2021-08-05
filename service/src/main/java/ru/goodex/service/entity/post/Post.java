package ru.goodex.service.entity.post;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.goodex.service.entity.profile.Profile;


@Data
@Getter
@Setter
@Entity(name = "post")
public class Post {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "rating")
    private int rating;
    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Profile profile;
}
