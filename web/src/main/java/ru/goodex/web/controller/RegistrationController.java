package ru.goodex.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.goodex.web.entity.Users;
import ru.goodex.web.service.UserService;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String toRegPage() {
        return "registration";
    }

    @PostMapping("/registrationAction")
    public String register(@Validated Users user, @RequestParam("file") MultipartFile file, Model model) {
        if (file != null && (userService.getUserByEmail(user.getEmail()) == null && userService.getUserByUserName(user.getUserName()) == null)) {
            userService.register(user, file);
            return "home";
        }
        model.addAttribute("UserAlreadyExist", "пользователь уже существует");
        return "registration";
    }
}
