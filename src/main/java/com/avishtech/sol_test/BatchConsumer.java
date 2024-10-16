package com.avishtech.sol_test;

import com.solace.spring.cloud.stream.binder.messaging.SolaceBinderHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Consumer;

@Component
public class BatchConsumer {
    private static final Logger logger = LoggerFactory.getLogger(BatchConsumer.class);

    @Bean
    public Consumer<Message<List<String>>> bulkConsume() {
        return batchMsg -> {
            List<?> data = batchMsg.getPayload();
            MessageHeaders msgHeader = batchMsg.getHeaders();
            List<?> listHeader = (List<?>) msgHeader.get(SolaceBinderHeaders.BATCHED_HEADERS);
            logger.info("Received batch size : {}", data.size());
            for (int i = 0; i < data.size(); i++) {
                logger.info("Batch Header : {}", listHeader.get(i));
                logger.info("Batch payload : {}", new String((byte[]) data.get(i), StandardCharsets.UTF_8));

            }
        };
    }

}
