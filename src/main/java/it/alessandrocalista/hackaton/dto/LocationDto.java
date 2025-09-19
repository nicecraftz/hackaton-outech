package it.alessandrocalista.hackaton.dto;

import it.alessandrocalista.hackaton.entity.Location;

import java.io.Serializable;

/**
 * DTO for {@link Location}
 */
public record LocationDto(
        Long id,
        double longitude,
        double latitude,
        String state,
        String region
) implements Serializable {
    public static LocationDto from(Location location) {
        return new LocationDto(location.getId(),
                location.getLongitude(),
                location.getLatitude(),
                location.getState(),
                location.getRegion());
    }
}