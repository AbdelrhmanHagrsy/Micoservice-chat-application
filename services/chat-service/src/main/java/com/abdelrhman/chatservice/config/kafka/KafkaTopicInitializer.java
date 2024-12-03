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
        /*
        Properties properties = new Properties();
        properties.put("bootstrap.servers", BOOTSTRAP_SERVERS_URL);

        // create order created topic  in kafka if they are not already exist
        try (AdminClient adminClient = AdminClient.create(properties)) {
            NewTopic instanceOneTopic = TopicBuilder.name("instance-forward-instanceOne")
                    .partitions(1)
                    .replicas(1)
                    .build();
            NewTopic instanceTwoTopic = TopicBuilder.name("instance-forward-instanceTwo")
                    .partitions(1)
                    .replicas(1)
                    .build();

            adminClient.createTopics(Arrays.asList(instanceOneTopic,instanceTwoTopic)).all().get();
            System.out.println("Topics created successfully.");
        } catch (Exception e) {
            if (e.getCause() instanceof TopicExistsException) {
                System.out.println("Topic already exists.");
            } else {
                throw new RuntimeException("Error creating topic", e);
            }
        }*/
    }
}