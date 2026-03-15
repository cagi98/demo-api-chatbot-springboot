package com.demo.restapi.handler;

import com.demo.restapi.exception.ChatBotAuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * Gestionnaire personnalisé pour erreurs d'autorisation.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Qualifier("handlerExceptionResolver")

    private final HandlerExceptionResolver resolver;

    public CustomAccessDeniedHandler(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws ChatBotAuthorizationException {
        // On "lance" l'exception personnalisée en la déléguant au resolver
        resolver.resolveException(request, response, null, new ChatBotAuthorizationException("Non autorisé"));
    }
}
