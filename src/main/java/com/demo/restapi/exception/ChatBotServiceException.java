package com.demo.restapi.exception;

/**
 * Exception personnalisée à lever si le chatbot client lève une exception.
 */
public class ChatBotServiceException extends RuntimeException {

    public ChatBotServiceException(String message) {
        super(message);
    }
}
