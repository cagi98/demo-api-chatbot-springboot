package com.demo.restapi.controller;

import com.demo.restapi.configuration.SecurityConfig;
import com.demo.restapi.dto.ChatDto;
import com.demo.restapi.exception.ChatbotBadRequestException;
import com.demo.restapi.handler.CustomAccessDeniedHandler;
import com.demo.restapi.handler.CustomAuthenticationEntryPoint;
import com.demo.restapi.handler.GlobalHandlerException;
import com.demo.restapi.service.ChatbotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(ChatBotController.class)
@Import({SecurityConfig.class, CustomAuthenticationEntryPoint.class, CustomAccessDeniedHandler.class, GlobalHandlerException.class})
public class ChatBotControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    ChatbotService chatbotService;

    @Test
    @WithMockUser
    void shouldReturnBadRequestHttpStatus_WhenQuestionNotGiven() throws Exception {
        when(chatbotService.chat(null, null)).thenThrow(ChatbotBadRequestException.class);

        mockMvc.perform(post("/v1/chat")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new ChatDto.ChatRequest(null, null)))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldReturnAnswer_WhenQuestionGiven() throws Exception {
        Pair<UUID, String> result = new Pair<>(null, "test");
        when(chatbotService.chat(null, "test")).thenReturn(result);

        ChatDto.ChatRequest chatRequest = new ChatDto.ChatRequest(result.getValue0(), result.getValue1());

        mockMvc.perform(post("/v1/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(chatRequest))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
