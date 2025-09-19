package it.alessandrocalista.hackaton.controller;

import it.alessandrocalista.hackaton.payload.ChatbotPayload;
import it.alessandrocalista.hackaton.response.ChatbotResponse;
import it.alessandrocalista.hackaton.service.OpenAIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/chatbot")
public class ChatbotController {
    private final OpenAIService openAIService;

    public ChatbotController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public ChatbotResponse ask(@RequestBody ChatbotPayload.Message message) {
        log.info("Received ask request: {}", message);
        String text = openAIService.ask(message);
        log.info("Replied to ask request");
        return new ChatbotResponse(text);
    }
}
