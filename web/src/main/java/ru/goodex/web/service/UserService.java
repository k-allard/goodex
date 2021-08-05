package ru.goodex.web.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import ru.goodex.web.entity.dto.RegistrationDTO;
import ru.goodex.web.entity.dto.UserDTO;
import ru.goodex.web.exception.UserAlreadyExistException;
import ru.goodex.web.exception.UserNotFoundException;

public interface UserService {

    void register(RegistrationDTO user, MultipartFile file) throws IOException, UserAlreadyExistException;

    UserDTO login(String username, String password) throws IllegalArgumentException, UserNotFoundException;

    Boolean verification(String verificationCode) throws UserNotFoundException;

}
