package it.alessandrocalista.hackaton.service;

import it.alessandrocalista.hackaton.dto.UserDto;
import it.alessandrocalista.hackaton.entity.Score;
import it.alessandrocalista.hackaton.entity.User;
import it.alessandrocalista.hackaton.payload.UserPayload;
import it.alessandrocalista.hackaton.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto loginEitherRegister(UserPayload.Login payload) {
        Optional<User> existingUser = userRepository.findByUsername(payload.username());
        if (existingUser.isPresent()) return UserDto.from(existingUser.get());

        User newUser = new User();
        newUser.setUsername(payload.username());
        newUser.setScore(new Score());

        User savedUser = userRepository.save(newUser);
        return UserDto.from(savedUser);
    }

    public void updateScore(UserPayload.UpdateScore payload) {
        String username = payload.username();
        Optional<User> byUsername = userRepository.findByUsername(username);

        if (byUsername.isEmpty()) return;

        User user = byUsername.get();
        Score score = user.getScore();
        score.setPoints(payload.points());
        score.setCurrentStreak(payload.currentStreak());
        userRepository.save(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserDto::from).toList();
    }
}
