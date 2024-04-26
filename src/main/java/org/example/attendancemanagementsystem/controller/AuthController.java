package org.example.attendancemanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.attendancemanagementsystem.dto.user.UserRequestDTO;
import org.example.attendancemanagementsystem.dto.user.UserResponseDTO;
import org.example.attendancemanagementsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @GetMapping("/sign-up")
    public String singUp() {
        return "sing-up";
    }

    @GetMapping("/sign-in")
    public String login() {
        return "index";
    }

    @PostMapping("/sing-up")
    public String singUpUser(@ModelAttribute UserRequestDTO userRequestDTO, Model model) {
        UserResponseDTO userResponseDTO = userService.crateUser(userRequestDTO);
        model.addAttribute("userId", userResponseDTO.getId());
        return "menu";
    }

    @PostMapping("/sing-in")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UserResponseDTO userResponseDTO = userService.singInUser(username, password);
        model.addAttribute("userId", userResponseDTO.getId());
        return "menu";
    }

    @PostMapping("verify-code")
    public void verificationCode(@RequestParam("verificationCode") String verificationCode, Model model) {
        userService.verifyCode(UUID.fromString(Objects.requireNonNull(model.getAttribute("userId")).toString()), verificationCode);
    }
}
