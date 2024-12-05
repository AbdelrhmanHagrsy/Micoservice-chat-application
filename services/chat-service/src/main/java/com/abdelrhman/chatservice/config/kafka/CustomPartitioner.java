package com.abdelrhman.chatservice.config.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

import static com.abdelrhman.chatservice.util.Constant.KafkaConst.KAFKA_MESSAGE_TOPIC_PARTITION_ONE;
import static com.abdelrhman.chatservice.util.Constant.KafkaConst.KAFKA_MESSAGE_TOPIC_PARTITION_TWO;

public class CustomPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // Map instance ID to partition (e.g., instance-one -> partition 0, instance-two -> partition 1)
        if (KAFKA_MESSAGE_TOPIC_PARTITION_ONE.equals(key)) {
            return 0; // Direct to partition 0
        } else if (KAFKA_MESSAGE_TOPIC_PARTITION_TWO.equals(key)) {
            return 1; // Direct to partition 1
        }

        // Default partition (can add more logic here for more instances)
        return Math.abs(key.hashCode()) % cluster.partitionCountForTopic(topic);
    }

    @Override
    public void close() {
        // Cleanup if needed
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // Configuration setup if needed
    }
}