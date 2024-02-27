package org.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;


//By default, it uses default values of the partition and the replication factor as 1.
//@Configuration
public class C01KafkaTopicConfig {
  //@Bean
    public NewTopic createTopic(){
        return TopicBuilder.name("product4")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas","2"))
                .build();
    }
}
