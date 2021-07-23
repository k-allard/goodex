package ru.goodex.web.service;

import org.springframework.web.multipart.MultipartFile;
import ru.goodex.web.entity.DTO.UserDTO;
import ru.goodex.web.entity.Users;

public interface UserService {

    void register(Users user, MultipartFile file);

    UserDTO login(String username, String password) throws IllegalArgumentException;

    Boolean verification(String username);



}
