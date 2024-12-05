package com.abdelrhman.chatservice.config.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

import static com.abdelrhman.chatservice.util.Constant.KafkaConst.*;


@Component
public class KafkaTopicInitializer implements ApplicationListener<ContextRefreshedEvent> {



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createTopicIfNotExists();
    }

    private void createTopicIfNotExists() {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", BOOTSTRAP_SERVERS_URL);

        // create a new topic for each chat-service you will run
        try (AdminClient adminClient = AdminClient.create(properties)) {
            NewTopic messageTopic = TopicBuilder.name(MESSAGE_TOPIC_NAME)  // create topic and partition number is equal two instance number
                    .partitions(2)
                    .replicas(1)
                    .build();

            adminClient.createTopics(Arrays.asList(messageTopic)).all().get();
            System.out.println("Topics created successfully.");
        } catch (Exception e) {
            if (e.getCause() instanceof TopicExistsException) {
                System.out.println("Topic already exists.");
            } else {
                throw new RuntimeException("Error creating topic", e);
            }
        }
    }
}