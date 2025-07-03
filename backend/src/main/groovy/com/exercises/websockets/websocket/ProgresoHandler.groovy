package com.exercises.websockets.websocket

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

import java.util.concurrent.ConcurrentHashMap

class ProgresoHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>()

    @Override
    void afterConnectionEstablished(WebSocketSession session) {
        sessions.put(session.id, session)
        println "Conectado: ${session.id}"
    }

    @Override
    void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session.id)
        println "Desconectado: ${session.id}"
    }

    void enviarMensaje(String mensaje) {
        sessions.values().each { session ->
            try {
                session.sendMessage(new TextMessage(mensaje))
            } catch (IOException e) {
                e.printStackTrace()
            }
        }
    }
}