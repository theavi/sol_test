package com.avishtech.sol_test;

import com.solace.spring.cloud.stream.binder.util.SolaceAcknowledgmentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.StaticMessageHeaderAccessor;
import org.springframework.integration.acks.AckUtils;
import org.springframework.integration.acks.AcknowledgmentCallback;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ManualAck {
    private static final Logger logger = LoggerFactory.getLogger(ManualAck.class);

    @Bean
    public Consumer<Message<String>> clientAck() {
        return v -> {
            logger.info("Received : {}", v.getPayload());
            AcknowledgmentCallback ackCallBck = StaticMessageHeaderAccessor.getAcknowledgmentCallback(v);
            ackCallBck.noAutoAck();
            String cid = (String) v.getHeaders().get("solace_correlationId");
            if (cid == null) {
                cid = "none";
            }
            try {
                if (cid.equals("accept")) {
                    logger.info("Accepting the Message.");
                    AckUtils.accept(ackCallBck);
                } else if (cid.equals("requeue")) {
                    logger.info("Requeuing the Message");
                    AckUtils.requeue(ackCallBck);
                    Thread.sleep(60000);
                } else {
                    logger.info("Rejecting the Message.");
                    AckUtils.reject(ackCallBck);
                    Thread.sleep(60000);
                }
            } catch (SolaceAcknowledgmentException | InterruptedException e) {
                logger.warn("Warning, exception occurred but message will be re-queued on broker and re-delivered", e);
//                return null; //don't send output message
            }
        };
    }
}
