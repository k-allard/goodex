package ru.goodex.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.goodex.web.entity.DTO.RegistrationDTO;
import ru.goodex.web.entity.DTO.UserDTO;
import ru.goodex.web.entity.Users;
import ru.goodex.web.entity.mappers.UserMapper;
import ru.goodex.web.exception.UserAlreadyExistException;
import ru.goodex.web.exception.UserNotFoundException;
import ru.goodex.web.jwt.JwtTokenProvider;
import ru.goodex.web.mailsender.MailSenderImpl;
import ru.goodex.web.repo.UsersRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {



    @Value("${upload.path}")
    private String uploadPath;

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailSenderImpl mailSender;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder,
                           UserMapper userMapper, JwtTokenProvider jwtTokenProvider, MailSenderImpl mailSender) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.mailSender = mailSender;
    }

    public void register(RegistrationDTO user, MultipartFile file) throws IOException, UserAlreadyExistException {
        if (getUserByEmail(user.getEmail()) != null || getUserByUserName(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User with this email or username already exist");
        }
        String imagePath;
        if (file != null) {
            createDirectory();
            imagePath = saveFile(file);
        } else {
            imagePath = "standardImagePath";
        }

        Users newUser = userMapper.convertFromRegistrationDTO(user, imagePath);
        usersRepository.save(newUser);

        String message = String.format(
                "Hello %s! \n" +
                        "Welcome to Goodex!. Your verification code is %s",
                newUser.getUserName(),
                newUser.getVerificationCode()
        );
        mailSender.send(user.getEmail(),"Welcome to Goodex!",message);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(uploadPath + "/" + resultFilename));
        return resultFilename;
    }

    private void createDirectory() {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    @Override
    public UserDTO login(String username, String password) throws IllegalArgumentException, UserNotFoundException {
        Users user = usersRepository.findUsersByUserName(username);
        if(user==null){
            throw new UserNotFoundException("User with this username was not found");
        }
        if(!user.isActive()){
            throw new IllegalStateException("This user is not active. Activate please this account!");
        }
        if (passwordEncoder.matches(password, user.getPassword()))
            return userMapper.convertFromUserEntity(user, jwtTokenProvider.createToken(username, user.getRole(), user.getId()));
        else {
            throw new IllegalArgumentException("Username or password is incorrect");
        }
    }

    @Override
    public Boolean verification(String verificationCode) throws UserNotFoundException {
        Users user = usersRepository.findUsersByVerificationCode(verificationCode)
                .orElseThrow(()->new UserNotFoundException("User with this verification code was not found"));
        user.setActive(true);
        usersRepository.save(user);
        return true;
    }

    private Users getUserByEmail(String email) {
        return usersRepository.findUsersByEmail(email);
    }

    private Users getUserByUserName(String userName) {
        return usersRepository.findUsersByUserName(userName);
    }

}
