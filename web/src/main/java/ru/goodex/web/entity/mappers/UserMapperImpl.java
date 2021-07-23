package ru.goodex.web.entity.mappers;

import org.springframework.stereotype.Component;
import ru.goodex.web.entity.DTO.UserDTO;
import ru.goodex.web.entity.Roles;
import ru.goodex.web.entity.Users;

import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO convertFromUserEntity(Users user, String token) {
        return new UserDTO(user.getId(), user.getEmail(), user.getUserName(), token,
                user.getRole().stream()
                .map(Roles::getName)
                .collect(Collectors.toList())
        );

    }
}
