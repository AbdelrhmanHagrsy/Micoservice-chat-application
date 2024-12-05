package com.abdelrhman.chatservice.controller;

import com.abdelrhman.chatservice.dto.MessageDto;
import com.abdelrhman.chatservice.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static com.abdelrhman.chatservice.util.Constant.KafkaConst.MESSAGE_TOPIC_NAME;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    private final RedisService redisService;


    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    @Autowired
    private Environment env;


    @MessageMapping("/sendPublicMessage")
    public void sendPublicMessage(MessageDto messageDto) {

        String recipientEmail = messageDto.getRecipients().get(0);
        String recipientInstanceId = redisService.getUserInstanceId(recipientEmail);

        if(env.getProperty("spring.application.instance.id").equals(recipientInstanceId)) {
            // In case recipient user is connected to current instance
            log.info("Sending to user: " + recipientEmail + " at /privateTopic/messages");
            messagingTemplate.convertAndSendToUser(recipientEmail, "/privateTopic/messages", messageDto.getMessageContent());
        }else{
            // in case recipient user is connected to other instance
            // use kafka topic name according to recipient instance which he connected to
            kafkaTemplate.send(MESSAGE_TOPIC_NAME,recipientInstanceId,messageDto);

        }

    }
}