package com.abdelrhman.chatservice.service;

import com.abdelrhman.chatservice.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static com.abdelrhman.chatservice.util.Constant.KafkaConst.CHAT_SERVICE_GROUP_ID;
import static com.abdelrhman.chatservice.util.Constant.KafkaConst.MESSAGE_TOPIC_NAME_FOR_CURRENT_INSTANCE;

@Service
public class KafkaConsumerService {

    @Autowired
    private  SimpMessagingTemplate messagingTemplate;


    @KafkaListener(topics = "#{T(java.lang.String).format('instance-forward-%s', @environment.getProperty('spring.application.instance.id'))}" ,groupId = CHAT_SERVICE_GROUP_ID,containerFactory = "newMessageKafkaListenerFactory")
    public void consumerMessage(MessageDto messageDto){
        System.out.println("Sending to user: " + messageDto.getRecipients().get(0) + " at /privateTopic/messages");
        messagingTemplate.convertAndSendToUser(messageDto.getRecipients().get(0), "/privateTopic/messages", messageDto.getMessageContent());
    }


}
