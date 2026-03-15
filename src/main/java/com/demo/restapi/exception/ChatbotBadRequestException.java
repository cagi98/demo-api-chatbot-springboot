package com.demo.restapi.exception;

/**
 * Exception personnalisée à lever pour échec de validation de donnée.
 */
public class ChatbotBadRequestException extends RuntimeException{

    public ChatbotBadRequestException(String message) {
        super(message);
    }
}
