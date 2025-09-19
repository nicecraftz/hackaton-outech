package it.alessandrocalista.hackaton.dto;

import it.alessandrocalista.hackaton.entity.Score;

import java.io.Serializable;

/**
 * DTO for {@link Score}
 */
public record ScoreDto(
        Long id,
        Long userId,
        Long points,
        Long currentStreak
) implements Serializable {

    public static ScoreDto from(Score score) {
        return new ScoreDto(score.getId(), score.getUser().getId(), score.getPoints(), score.getCurrentStreak());
    }
}