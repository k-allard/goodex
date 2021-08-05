package ru.goodex.web.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.goodex.web.entity.DTO.RegistrationDTO;
import ru.goodex.web.exception.UserAlreadyExistException;
import ru.goodex.web.service.UserServiceImpl;


@Controller
public class RegistrationController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/registration")
    public String toRegPage() {
        return "registration";
    }

    @PostMapping("/registrationAction")
    public String register(@Validated RegistrationDTO user, @RequestParam("file") MultipartFile file, Model model) {
        if (file != null && (userService.getUserByEmail(user.getEmail()) == null
                && userService.getUserByUserName(user.getUsername()) == null)) {
            try {
                userService.register(user, file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UserAlreadyExistException e) {
                e.printStackTrace();
            }
            return "home";
        }
        model.addAttribute("UserAlreadyExist", "пользователь уже существует");
        return "registration";
    }
}
