package it.alessandrocalista.hackaton.dto;

import it.alessandrocalista.hackaton.entity.Category;

import java.io.Serializable;

/**
 * DTO for {@link Category}
 */
public record CategoryDto(
        Long id,
        String name
) implements Serializable {

    public static CategoryDto from(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}