package com.exercises.websockets.websocket

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    ProgresoHandler progresoHandler() {
        return new ProgresoHandler()
    }

    @Override
    void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(progresoHandler(), "/ws/progreso").setAllowedOrigins("*")

    }
}