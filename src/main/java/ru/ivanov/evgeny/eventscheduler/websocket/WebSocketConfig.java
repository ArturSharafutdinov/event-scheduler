package ru.ivanov.evgeny.eventscheduler.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        http://localhost:8080. if * - ругается на jwtFilter.TODO refactor
        registry.addEndpoint("/ws").setAllowedOrigins(frontendUrl).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(
                "/chat",
                "/app",
                "/info",
                "/eventChat"
        );
        registry.setApplicationDestinationPrefixes("/");
    }
}