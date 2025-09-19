package it.alessandrocalista.hackaton.dto;

import it.alessandrocalista.hackaton.entity.HeritageImage;

import java.io.Serializable;

/**
 * DTO for {@link HeritageImage}
 */
public record HeritageImageDto(
        Long id,
        Long heritageId,
        String date,
        String dateAccessed,
        String author,
        String copyright,
        String source,
        String link,
        String license,
        String filePath
) implements Serializable {

    public static HeritageImageDto from(HeritageImage image) {
        return new HeritageImageDto(image.getId(),
                image.getHeritage().getId(),
                image.getDate(),
                image.getDateAccessed(),
                image.getAuthor(),
                image.getCopyright(),
                image.getSource(),
                image.getLink(),
                image.getLicense(),
                image.getFilePath());
    }
}