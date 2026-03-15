package com.demo.restapi.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception personnalisée à lever pour échec d'authentication.
 */
public class ChatBotAuthenticationException extends AuthenticationException {

    public ChatBotAuthenticationException(String message) {
        super(message);
    }
}
