package com.exercises.websockets

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class ChatController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    Message send(Message message) {
        return message
    }
}
