package ru.goodex.service.entity.profile;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.goodex.service.entity.post.Post;


@Data
@Setter
@Getter
@Entity(name = "profile")
public class Profile {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "secondname")
    private String secondName;
    @Column(name = "image")
    private String image;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @ManyToMany
    @JoinColumn(name = "profile_id")
    private List<Profile> friends;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Post> posts;
}
