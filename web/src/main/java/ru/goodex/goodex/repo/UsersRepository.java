package ru.goodex.goodex.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goodex.goodex.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    Users findUsersByEmail(String email);
    Users findUsersByUserName(String userName);
}
