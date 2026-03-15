package com.demo.restapi.exception;

import org.springframework.security.access.AccessDeniedException;

/**
 * Exception personnalisée à lever pour échec d'authorisation.
 */
public class ChatBotAuthorizationException extends AccessDeniedException {

    public ChatBotAuthorizationException(String message) {
        super(message);
    }
}
