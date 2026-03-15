package com.demo.restapi.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Ma classe de DTO.
 */
public class ChatDto {

    public static record ChatRequest(@Nullable UUID chatId, @NotNull String question) {}

    public static record ChatResponse(UUID chatId, String answer) {}
}
