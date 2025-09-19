package it.alessandrocalista.hackaton.dto;

import it.alessandrocalista.hackaton.entity.Heritage;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Heritage}
 */
public record HeritageDto(
        Long id,
        LocationDto location,
        String name,
        String description,
        CategoryDto category,
        String inscribedDate,
        List<HeritageImageDto> images
) implements Serializable {

    public static HeritageDto from(Heritage heritage) {
        return new HeritageDto(heritage.getId(),
                LocationDto.from(heritage.getLocation()),
                heritage.getName(),
                heritage.getDescription(),
                CategoryDto.from(heritage.getCategory()),
                heritage.getInscribedDate(),
                heritage.getImages().stream().map(HeritageImageDto::from).toList());
    }
}