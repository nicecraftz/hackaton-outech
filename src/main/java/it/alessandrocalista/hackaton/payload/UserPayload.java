package it.alessandrocalista.hackaton.payload;

import jakarta.validation.constraints.NotEmpty;

import javax.validation.constraints.Min;

public interface UserPayload {

    record Login(
            @NotEmpty(message = "username cannot be empty")
            String username
    ) {
    }

    record UpdateScore(
            @NotEmpty(message = "username cannot be empty")
            String username,
            @Min(value = 0, message = "Points cannot be below 0")
            long points,
            @Min(value = 0, message = "Streak cannot be below 0")
            long currentStreak
    ) {

    }
}