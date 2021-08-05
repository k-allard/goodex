package ru.goodex.service.repository.post;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goodex.service.entity.post.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    Post findPostById(UUID uuid);
}
