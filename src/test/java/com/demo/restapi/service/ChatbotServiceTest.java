package com.demo.restapi.service;

import com.demo.restapi.exception.ChatBotServiceException;
import com.demo.restapi.exception.ChatbotBadRequestException;
import lombok.extern.log4j.Log4j2;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;

import java.util.UUID;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Log4j2
public class ChatbotServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ChatClient chatClient;

    @InjectMocks
    private ChatbotService chatbotService;

    @Test
    void shouldThrowBadRequestException_WhenQuestionNotGiven() {
        assertThrowsExactly(ChatbotBadRequestException.class, () -> chatbotService.chat(null, null));
    }

    @Test
    void shouldThrowChatBotServiceException_WhenChatClientFails() {
        when(chatClient.prompt()).thenThrow(new RuntimeException("Model not found."));

        assertThrowsExactly(ChatBotServiceException.class, () -> chatbotService.chat(null, "test"));
    }

    @Test
    void shouldReturnAnswer_WhenQuestionGiven() {
        when(chatClient.prompt().user(anyString()).advisors(any(Consumer.class)).call().content())
                .thenReturn("answer");

        Pair<UUID, String> result = chatbotService.chat(UUID.randomUUID(), "Hello");

        log.debug(result.getValue1());

        assertNotNull(result);
        assertEquals("answer", result.getValue1());
    }
}
