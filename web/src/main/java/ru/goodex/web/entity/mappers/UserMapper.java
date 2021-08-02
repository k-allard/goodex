package ru.goodex.web.entity.mappers;

import ru.goodex.web.entity.DTO.RegistrationDTO;
import ru.goodex.web.entity.DTO.UserDTO;
import ru.goodex.web.entity.Users;

public interface UserMapper {

    UserDTO convertFromUserEntity(Users user, String token);

    Users convertFromRegistrationDTO(RegistrationDTO registrationDTO,String imagePath);
}
