package com.abdelrhman.chatservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {
     private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendPublicMessage")
    public void sendPublicMessage(String message, Principal principal) {
        String userEmail = principal.getName();
        System.out.println("Sending to user: " + userEmail + " at /privateTopic/messages");
        messagingTemplate.convertAndSendToUser(message, "/privateTopic/messages", message);

    }
}
