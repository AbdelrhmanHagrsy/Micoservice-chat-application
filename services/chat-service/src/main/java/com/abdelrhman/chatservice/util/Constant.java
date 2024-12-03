package com.abdelrhman.chatservice.util;

import org.springframework.beans.factory.annotation.Value;

public class Constant {



    public final class KafkaConst{

        public static final  String BOOTSTRAP_SERVERS_URL = "localhost:9092";
        public static final  String CHAT_SERVICE_GROUP_ID = "chat-service-group";

        public static final String MESSAGE_TOPIC_NAME_FOR_CURRENT_INSTANCE = "instance-forward-";
    }

}
