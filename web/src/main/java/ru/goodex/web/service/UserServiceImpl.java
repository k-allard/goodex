package ru.goodex.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.goodex.web.entity.DTO.UserDTO;
import ru.goodex.web.entity.Users;
import ru.goodex.web.entity.mappers.UserMapper;
import ru.goodex.web.jwt.JwtTokenProvider;
import ru.goodex.web.repo.RolesRepository;
import ru.goodex.web.repo.UsersRepository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Value("${upload.path}")
    private String uploadPath;

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserMapper userMapper, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

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

    @Override
    public UserDTO login(String username, String password) throws IllegalArgumentException {
        Users user = usersRepository.findUsersByUserName(username);
        if(passwordEncoder.matches(password,user.getPassword()))
            return userMapper.convertFromUserEntity(user,jwtTokenProvider.createToken(username,user.getRole(),user.getId()));
        else{
            throw new IllegalArgumentException("Username or password is incorrect");
        }
    }

    @Override
    public Boolean verification(String username) {
        return null;
    }

    public Users getUserByEmail(String email){
        return usersRepository.findUsersByEmail(email);
    }
    public Users getUserByUserName(String userName){
        return usersRepository.findUsersByUserName(userName);
    }

}
