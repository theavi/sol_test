package com.avishtech.sol_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class DynamicPublishing {
    private static final Logger logger = LoggerFactory.getLogger(DynamicPublishing.class);

    @Bean
    public Consumer<Message<String>> publishEventOnDynamicTopic(StreamBridge sb) {
        return v -> {
            logger.info("Payload is: {}", v.getPayload());
            logger.info("solace_correlationId is : {}", v.getHeaders().get("solace_correlationId"));

            String cId = (String) v.getHeaders().get("solace_correlationId");
            if(cId==null){
                cId=Integer.toString(1);
            }
            String topic="solace/cid/".concat(cId);
            sb.send(topic,v.getPayload());
        };
    }
}
