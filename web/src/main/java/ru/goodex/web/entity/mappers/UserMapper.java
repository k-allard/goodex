package ru.goodex.web.entity.mappers;

import ru.goodex.web.entity.Users;
import ru.goodex.web.entity.dto.ProfileDTO;
import ru.goodex.web.entity.dto.RegistrationDTO;
import ru.goodex.web.entity.dto.UserDTO;

public interface UserMapper {

    UserDTO convertFromUserEntity(Users user, String token);

    Users convertFromRegistrationDTO(RegistrationDTO registrationDTO, String imagePath);

    ProfileDTO convertToProfileDTO(Users user);
}
