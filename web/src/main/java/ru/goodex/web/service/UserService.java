package ru.goodex.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.goodex.web.entity.Users;
import ru.goodex.web.repo.RolesRepository;
import ru.goodex.web.repo.UsersRepository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(Users user, MultipartFile file) {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            user.setImg(resultFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Collections.singleton(rolesRepository.findRolesByName("USER")));
        usersRepository.save(user);
    }

    public Users getUserByEmail(String email){
        return usersRepository.findUsersByEmail(email);
    }
    public Users getUserByUserName(String userName){
        return usersRepository.findUsersByUserName(userName);
    }
}