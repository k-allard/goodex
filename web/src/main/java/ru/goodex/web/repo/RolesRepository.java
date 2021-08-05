package ru.goodex.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.goodex.web.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {
    @Query(value = "SELECT * from Roles  where name = ?1",nativeQuery = true)
    Roles findRolesByName(String name);
}
