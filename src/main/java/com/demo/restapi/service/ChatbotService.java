package com.demo.restapi.service;

import com.demo.restapi.exception.ChatBotServiceException;
import com.demo.restapi.exception.ChatbotBadRequestException;
import io.micrometer.common.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.javatuples.Pair;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service qui gère la logique métier.
 */
@Log4j2
@Service
public class ChatbotService {

    final ChatClient chatClient;

    public ChatbotService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public Pair<UUID, String> chat(UUID chatIdp, String question) throws ChatbotBadRequestException, ChatBotServiceException {

        if (StringUtils.isBlank(question)) {
            throw new ChatbotBadRequestException("Veuillez entrer une question...");
        }

        UUID chatId = Optional
                .ofNullable(chatIdp)
                .orElse(UUID.randomUUID());

        String answer;

        try {
            log.debug(chatId);
            answer = chatClient
                    .prompt()
                    .user(question)
                    .advisors(advisorSpec ->
                            advisorSpec
                                    .param("chat_memory_conversation_id", chatId))
                    .call()
                    .content();

            log.info(answer);
        } catch (RuntimeException ex) {
            throw new ChatBotServiceException("Erreur de communication avec le model IA: " + ex.getMessage());
        }

        return new Pair<>(chatId, answer);
    }
}
