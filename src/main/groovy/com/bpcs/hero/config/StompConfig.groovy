package com.bpcs.hero.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry


/**
 * Stomp websocket messaging config
 */
@Configuration
@EnableWebSocketMessageBroker
class StompConfig extends AbstractWebSocketMessageBrokerConfigurer
{
    @Override
    void registerStompEndpoints(StompEndpointRegistry registry)
    {
        registry.addEndpoint("/stompex").setAllowedOrigins("*").withSockJS()
    }

    @Override
    void configureMessageBroker(MessageBrokerRegistry registry)
    {
        registry.setApplicationDestinationPrefixes("/app")
        registry.enableSimpleBroker("/topic", "/queue")
    }
}
