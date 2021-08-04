package ru.goodex.service.repository.profile;

import ru.goodex.service.entity.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Profile findProfileById(UUID uuid);
}
