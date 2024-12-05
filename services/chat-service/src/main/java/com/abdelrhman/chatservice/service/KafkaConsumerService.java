package com.abdelrhman.chatservice.service;

import com.abdelrhman.chatservice.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static com.abdelrhman.chatservice.util.Constant.KafkaConst.CHAT_SERVICE_GROUP_ID;
import static com.abdelrhman.chatservice.util.Constant.KafkaConst.MESSAGE_TOPIC_NAME;

@Service
@Slf4j
public class KafkaConsumerService {

    @Autowired
    private  SimpMessagingTemplate messagingTemplate;


    // each chat-service instance listen to specific kafka partition
    @KafkaListener(topics = MESSAGE_TOPIC_NAME, groupId = CHAT_SERVICE_GROUP_ID, containerFactory = "newMessageKafkaListenerFactory")
    public void consumerMessage(MessageDto messageDto) {

        // In case recipient user is connected to current instance
        log.info("Sending to user: " + messageDto.getRecipients().get(0) + " at /privateTopic/messages");
        messagingTemplate.convertAndSendToUser(messageDto.getRecipients().get(0), "/privateTopic/messages", messageDto.getMessageContent());

    }

}
