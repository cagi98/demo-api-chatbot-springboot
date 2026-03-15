package com.demo.restapi;

import com.demo.restapi.dto.ChatDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatBotIntegrationIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Test
    void shouldReturnBadRequestHttpStatus_WhenQuestionNotGiven() {
        UUID uuid = UUID.randomUUID();
        ChatDto.ChatRequest chatRequest = new ChatDto.ChatRequest(uuid, "");

        ResponseEntity<ChatDto.ChatResponse> response = restTemplate
                .withBasicAuth(username, password)
                .postForEntity("/v1/chat", chatRequest, ChatDto.ChatResponse.class);

        Assertions.assertSame(response.getStatusCode(), HttpStatusCode.valueOf(400));
    }

    @Test
    void shouldReturnAnswer_WhenQuestionGiven() {
        UUID uuid = UUID.randomUUID();
        ChatDto.ChatRequest chatRequest = new ChatDto.ChatRequest(uuid, "What is Spring Boot ?");

        ResponseEntity<ChatDto.ChatResponse> response = restTemplate
                .withBasicAuth(username, password)
                .postForEntity("/v1/chat", chatRequest, ChatDto.ChatResponse.class);

        Assertions.assertSame(response.getStatusCode(), HttpStatusCode.valueOf(200));
    }
}
