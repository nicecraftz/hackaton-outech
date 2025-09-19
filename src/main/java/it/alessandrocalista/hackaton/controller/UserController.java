package it.alessandrocalista.hackaton.controller;

import it.alessandrocalista.hackaton.dto.UserDto;
import it.alessandrocalista.hackaton.payload.UserPayload;
import it.alessandrocalista.hackaton.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody @Valid UserPayload.Login payload) {
        log.info("Received login request: {}", payload);
        UserDto userDto = userService.loginEitherRegister(payload);
        log.info("Logged in user: {}", userDto);
        return userDto;
    }

    @PostMapping("/points")
    public void updatePoints(@RequestBody @Valid UserPayload.UpdateScore payload) {
        log.info("Received update score request: {}", payload);
        userService.updateScore(payload);
        log.info("Updated score request");
    }


}
