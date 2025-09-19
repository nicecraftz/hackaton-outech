package it.alessandrocalista.hackaton.service;

import it.alessandrocalista.hackaton.SecurityConstant;
import it.alessandrocalista.hackaton.payload.ChatbotPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenAIService {
    private final ChatClient chatClient;

    public OpenAIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(OpenAiChatOptions.builder().build())
                .build();
    }

    public String ask(ChatbotPayload.Message payload) {
        ChatResponse chatResponse = chatClient.prompt()
                .system(SecurityConstant.SYSTEM_PROMPT + payload.heritageName())
                .user(payload.content())
                .call()
                .chatResponse();

        Generation result = chatResponse.getResult();
        return result.getOutput().getText();
    }
}