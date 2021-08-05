package ru.goodex.web.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.goodex.web.entity.dto.LoginDTO;
import ru.goodex.web.entity.dto.RegistrationDTO;
import ru.goodex.web.entity.dto.UserDTO;
import ru.goodex.web.exception.UserAlreadyExistException;
import ru.goodex.web.exception.UserNotFoundException;
import ru.goodex.web.service.UserServiceImpl;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final UserServiceImpl userServiceImpl;

    public AuthController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        try {
            UserDTO userDTO = userServiceImpl.login(loginDTO.getUsername(), loginDTO.getPassword());
            return ResponseEntity.ok(userDTO);
        } catch (IllegalArgumentException | IllegalStateException | UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestPart(name = "user") RegistrationDTO user,
                                   @RequestPart(name = "file", required = false) MultipartFile file) {
        ResponseEntity responseEntity;

        try {
            userServiceImpl.register(user, file);
            responseEntity = ResponseEntity.ok(true);
        } catch (UserAlreadyExistException | IOException e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }
        return responseEntity;
    }

    @PostMapping("/activation")
    public ResponseEntity activation(@RequestBody String verificationCode) {
        ResponseEntity responseEntity;
        try {
            userServiceImpl.verification(verificationCode);
            responseEntity = ResponseEntity.ok(true);
        } catch (UserNotFoundException e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }
        return responseEntity;
    }

}
