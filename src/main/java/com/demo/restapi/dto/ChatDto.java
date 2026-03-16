package com.demo.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Ma classe de DTO.
 */
public class ChatDto {

    public static record ChatRequest(
        @Nullable UUID chatId,
        @Schema(defaultValue = "Give me a sky blue color") @NotNull String question
    ) {}

    public static record ChatResponse(UUID chatId, String answer) {}
}
