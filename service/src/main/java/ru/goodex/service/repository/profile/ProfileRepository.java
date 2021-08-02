package ru.goodex.service.repository.profile;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.goodex.service.entity.profile.Profile;


public interface ProfileRepository extends JpaRepository<Profile, UUID> {
}
