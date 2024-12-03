package com.abdelrhman.chatservice.controller;

import com.abdelrhman.chatservice.dto.MessageDto;
import com.abdelrhman.chatservice.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.security.Principal;

import static com.abdelrhman.chatservice.util.Constant.KafkaConst.MESSAGE_TOPIC_NAME_FOR_CURRENT_INSTANCE;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    private final RedisService redisService;


    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    @Autowired
    private Environment env;


    @MessageMapping("/sendPublicMessage")
    public void sendPublicMessage(MessageDto messageDto) {

        String recipientId = messageDto.getRecipients().get(0);
        String userInstanceId = redisService.getUserInstanceId(recipientId);

        if(env.getProperty("spring.application.instance.id").equals(userInstanceId)) {
            // In case recipient user is connected to this instance
            System.out.println("Sending to user: " + recipientId + " at /privateTopic/messages");
            messagingTemplate.convertAndSendToUser(recipientId, "/privateTopic/messages", messageDto.getMessageContent());
        }else{
            // in case recipient user is connected to other instance
            kafkaTemplate.send(MESSAGE_TOPIC_NAME_FOR_CURRENT_INSTANCE+userInstanceId,messageDto);

        }

    }
}