package it.alessandrocalista.hackaton.payload;

import jakarta.validation.constraints.NotEmpty;

public interface ChatbotPayload {
    record Message(
            @NotEmpty(message = "The heritage cannot be empty.")
            String heritageName,
            @NotEmpty(message = "content cannot be empty.")
            String content
    ) {

    }
}
