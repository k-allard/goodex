package ru.goodex.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goodex.web.entity.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Users findUsersByEmail(String email);

    Users findUsersByUserName(String userName);

    Optional<Users> findUsersByVerificationCode(String verificationCode);
}
