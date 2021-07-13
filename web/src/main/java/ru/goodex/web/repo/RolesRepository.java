package ru.goodex.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goodex.web.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {
    Roles findRolesByName(String name);
}
