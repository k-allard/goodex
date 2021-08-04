package ru.goodex.web.entity.mappers;

import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import ru.goodex.web.entity.DTO.ProfileDTO;
import ru.goodex.web.entity.DTO.RegistrationDTO;
import ru.goodex.web.entity.DTO.UserDTO;
import ru.goodex.web.entity.Users;

public interface UserMapper {

    UserDTO convertFromUserEntity(Users user, String token);

    Users convertFromRegistrationDTO(RegistrationDTO registrationDTO,String imagePath);

    ProfileDTO convertToProfileDTO(Users user);
}
