package com.avishtech.sol_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class ConsumerErrorHandling {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerErrorHandling.class);

    @Bean
    public Function<Message<String>, String> bindingSpecificError() {
        return v -> {
            logger.info("Received : {}", v);
            // Logic to Avoid infinite loop of message being re-delivered when testing error
            // handling during codelab. DO NOT USE IN PRODUCTION CODE
            if ((Boolean) v.getHeaders().get("solace_redelivered")) {
                logger.warn("Exiting successfully to ACK msg and avoid infinite redelivieres: SPECIFIC");
                return null;
            }
            throw new RuntimeException("Oh no!");
        };
    }

    @Bean
    public Function<Message<String>, String> globalGenericError() {
        return v -> {
            logger.info("Received: " + v);
           /* if (!sendMessageDownstream(v)) {
                logger.warn("Not Sending an Outbound Message");
                return null; //Don't send a message, but ACCEPT it to remove it from the queue
            } else {
                return processMessage(v);
            }*/
            // Logic to Avoid infinite loop of message being re-delivered when testing error
            // handling during codelab. DO NOT USE IN PRODUCTION CODE
            if (true == (Boolean) v.getHeaders().get("solace_redelivered")) {
                logger.warn("Exiting successfully to ACK msg and avoid infinite redelivieres : GLOBAL");
                return null;
            }

            throw new RuntimeException("Oh no!");
        };
    }
}
