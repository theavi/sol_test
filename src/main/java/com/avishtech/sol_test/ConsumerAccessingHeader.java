package com.avishtech.sol_test;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
public class ConsumerAccessingHeader {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerAccessingHeader.class);

    @Bean
    public Consumer<Message<String>> consumingHeader() {
        return v -> {
            logger.info("All message Header list: {}", v.getHeaders());
            logger.info("solace_destination {}", v.getHeaders().get("solace_destination"));
            logger.info("solace_timeToLive {}", v.getHeaders().get("solace_timeToLive"));
            logger.info("Custom Header :{}",v.getHeaders().get("Key"));
        };
    }

    @Bean
    public Supplier<Message<String>> PublishHeader() {
        return ()-> {
            logger.info("Event published");
          return  MessageBuilder.withPayload("Hello Header").setHeader("Key", "Reyansh").build();
        };
    }
}
