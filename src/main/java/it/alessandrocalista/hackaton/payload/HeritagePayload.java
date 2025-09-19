package it.alessandrocalista.hackaton.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public interface HeritagePayload {

    record ImportPart(
            double longitude,
            double latitude,
            @NotEmpty(message = "state cannot be empty") String state,
            @NotEmpty(message = "region cannot be empty") List<String> region,
            @NotEmpty(message = "name cannot be empty") String name,
            @NotEmpty(message = "description cannot be empty") String description,
            @NotEmpty(message = "category cannot be empty") String category,
            @JsonProperty("inscribed_date") String inscribedDate
    ) {
    }
}
