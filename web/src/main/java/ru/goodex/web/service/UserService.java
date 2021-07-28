package ru.goodex.web.service;

import org.springframework.web.multipart.MultipartFile;
import ru.goodex.web.entity.DTO.RegistrationDTO;
import ru.goodex.web.entity.DTO.UserDTO;
import ru.goodex.web.entity.Users;
import ru.goodex.web.exception.UserAlreadyExistException;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public interface UserService {

    void register(RegistrationDTO user, MultipartFile file) throws IOException, UserAlreadyExistException;

    UserDTO login(String username, String password) throws IllegalArgumentException;

    Boolean verification(String username);



}
