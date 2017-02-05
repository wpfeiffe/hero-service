package com.bpcs.hero.config

import com.bpcs.hero.websocket.CounterHandler
import com.bpcs.hero.websocket.EchoHandler
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

import javax.inject.Inject

/**
 * WebSocket spring configuration
 */
@Configuration
@EnableWebSocket
@EnableScheduling
class WebSocketConfig implements WebSocketConfigurer
{
    @Inject
    CounterHandler counterHandler

    @Inject
    EchoHandler echoHandler

    @Override
    void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {
        registry.addHandler(counterHandler, "/counter").setAllowedOrigins("*")
        registry.addHandler(echoHandler, "/wsecho").setAllowedOrigins("*")
    }

}
