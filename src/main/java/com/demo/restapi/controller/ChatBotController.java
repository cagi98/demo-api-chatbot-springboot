package com.demo.restapi.controller;

import com.demo.restapi.dto.ChatDto;
import com.demo.restapi.exception.ChatBotServiceException;
import com.demo.restapi.exception.ChatbotBadRequestException;
import com.demo.restapi.service.ChatbotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.javatuples.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controlleur pour gérér les interactions avec le ChatBot.
 */
@RestController
@Tag(name = "ChatBot")
public class ChatBotController {

    private final ChatbotService chatbotService;

    public ChatBotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    /**
     * Envoie un message au chatBot et retourne un reponse.
     *
     * @param chatRequest {@link ChatDto.ChatRequest} Le DTO de request.
     * @return Une {@link ResponseEntity}.
     * @throws ChatbotBadRequestException Si la validation des données échoue.
     * @throws ChatBotServiceException Si le chatbotClient lève une exception.
     */
    @Operation(summary = "Chatter")
    @PostMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChatDto.ChatResponse> chat(@Valid @RequestBody ChatDto.ChatRequest chatRequest) throws ChatbotBadRequestException, ChatBotServiceException {
        Pair<UUID, String> answer = chatbotService.chat(chatRequest.chatId(), chatRequest.question());
        ChatDto.ChatResponse chatResponse = new ChatDto.ChatResponse(answer.getValue0(), answer.getValue1());
        return ResponseEntity.ok(chatResponse);
    }
}
