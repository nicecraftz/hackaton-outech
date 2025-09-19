package it.alessandrocalista.hackaton.dto;

import it.alessandrocalista.hackaton.entity.User;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDto(
        Long id,
        String username,
        ScoreDto score
) implements Serializable {

    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                ScoreDto.from(user.getScore())
        );
    }
}