package ru.goodex.web.entity.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.goodex.web.entity.DTO.RegistrationDTO;
import ru.goodex.web.entity.DTO.UserDTO;
import ru.goodex.web.entity.Role;
import ru.goodex.web.entity.Roles;
import ru.goodex.web.entity.Users;
import ru.goodex.web.repo.RolesRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserMapperImpl(PasswordEncoder passwordEncoder, RolesRepository rolesRepository) {
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserDTO convertFromUserEntity(Users user, String token) {
        List<String> roles = user.getRole().stream()
                .map(Roles::getRole)
                .map(Enum::toString)
                .collect(Collectors.toList());
        return new UserDTO(user.getId(), user.getEmail(), user.getUserName(), token, roles, user.isActive());

    }

    @Override
    public Users convertFromRegistrationDTO(RegistrationDTO registrationDTO, String imagePath) {
        Users user = new Users();
        user.setEmail(registrationDTO.getEmail());
        user.setUserName(registrationDTO.getUsername());
        user.setImg(imagePath);
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setId(UUID.randomUUID());
        Roles roles = rolesRepository.findRolesByName(Role.User.getName());
        user.setRole(Set.of(roles));
        user.setActive(false);
        user.setVerificationCode(UUID.randomUUID().toString());
        return user;
    }
}
