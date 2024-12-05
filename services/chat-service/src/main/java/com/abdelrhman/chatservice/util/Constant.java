package com.abdelrhman.chatservice.util;

import org.springframework.beans.factory.annotation.Value;

public class Constant {



    public final class KafkaConst{

        public static final  String BOOTSTRAP_SERVERS_URL = "localhost:9092";
        public static final  String CHAT_SERVICE_GROUP_ID = "chat-service-group";
        public static final String MESSAGE_TOPIC_NAME = "chat-messages-topic";
        public static final String KAFKA_MESSAGE_TOPIC_PARTITION_ONE = "instance-one";
        public static final String KAFKA_MESSAGE_TOPIC_PARTITION_TWO = "instance-two";



    }

}
