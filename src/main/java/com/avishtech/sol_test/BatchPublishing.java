package com.avishtech.sol_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

@Component
public class BatchPublishing {
    private static final Logger logger = LoggerFactory.getLogger(BatchPublishing.class);

    @Bean
    public Function<Message<String>, Collection<Message<String>>> bulkPublish() {
        return v -> {
            logger.info("Received Event for Bulk push: {}",v);
            ArrayList<Message<String>> msgList = new ArrayList<Message<String>>();
            msgList.add(MessageBuilder.withPayload("Happy").build());
            msgList.add(MessageBuilder.withPayload("SAD").build());
            msgList.add(MessageBuilder.withPayload("ANGRY").build());
            return msgList;
        };
    }
}
