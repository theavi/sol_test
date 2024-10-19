package com.avishtech.sol_test.errrorHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class HandleGlobalError {
    private static final Logger logger = LoggerFactory.getLogger(HandleGlobalError.class);

    @Bean
    public Consumer<ErrorMessage> globalErrorHandler() {
        return message -> {
            logger.info("Global errorChannel received msg. NO BUSINESS LOGIC HERE! Notify ONLY! {}", message.toString());
            logger.info("Original Message: {}", message.getOriginalMessage());
        };
    }

}
