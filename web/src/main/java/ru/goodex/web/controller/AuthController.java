package ru.goodex.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.goodex.web.entity.DTO.LoginDTO;
import ru.goodex.web.entity.DTO.RegistrationDTO;
import ru.goodex.web.entity.DTO.UserDTO;
import ru.goodex.web.entity.Users;
import ru.goodex.web.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity register(@Validated Users user,
                                   @RequestParam("file") MultipartFile file) {
        ResponseEntity responseEntity;
        if (file != null && (userServiceImpl.getUserByEmail(user.getEmail()) == null &&
                userServiceImpl.getUserByUserName(user.getUserName()) == null)) {
            userServiceImpl.register(user, file);
            responseEntity = ResponseEntity.ok(true);
        } else {
            responseEntity = ResponseEntity.badRequest().body("User with this ID already exist");
        }
        return responseEntity;
    }

}
