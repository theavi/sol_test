package com.avishtech.sol_test.errrorHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class HandleBindingError {
    private static final Logger logger = LoggerFactory.getLogger(HandleBindingError.class);

     /*if you are using Client/Manual acknowledgements you can also use them in the binding specific error handler*/

    @Bean
    public Consumer<ErrorMessage> bindingErrorHandler() {
        return message -> {
            logger.info("Binding Specific Error Handler executing business logic for: {}", message.toString());
            logger.info("Original Message : {}", message.getOriginalMessage());
        };
    }
}
