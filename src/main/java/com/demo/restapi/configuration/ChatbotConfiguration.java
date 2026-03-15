package com.demo.restapi.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  Configuration de l'infrastructure du ChatBot.
 *  Définit les composants nécessaires à la gestion de la mémoire
 *  et à l'interaction avec le modèle de langage (LLM).
 */
@Configuration
public class ChatbotConfiguration {

    /**
     * Définit le système de stockage en mémoire de l'historique des conversations.
     *
     * @return Une instance de {@link InMemoryChatMemory} pour conserver le contexte.
     */
    @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    /**
     * Configure le client de chat principal utilisé par la couche Service.
     *
     * @param chatModel Le modèle LLM.
     * @param chatMemory Le bean de mémoire défini ci-dessus.
     * @return Un {@link ChatClient}
     */
    @Bean
    public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory) {
        return ChatClient
                .builder(chatModel)
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .build();
    }
}
