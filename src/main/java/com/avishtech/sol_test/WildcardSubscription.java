package com.avishtech.sol_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class WildcardSubscription {

    private static Logger logger = LoggerFactory.getLogger(WildcardSubscription.class);

    @Bean
    public Consumer<Message<String>> wildcardSubscribe() {
        return v -> {
            logger.info("Received :{}", v);
        };
    }

    @Bean
    public Consumer<Message<String>> additionalQueueSubscription() {
        return v -> {
            logger.info("Message received from: {},{}", v.getHeaders().get("solace_destination"), v.getPayload());
        };
    }
}

