package com.avishtech.sol_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class SolTestApplication {
    private static final Logger logger = LoggerFactory.getLogger(SolTestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SolTestApplication.class, args);
    }

    /*Non durable Queue*/
    @Bean
    public Consumer<String> nonDurableQueue() {
        return v -> {
            System.out.println("Received at non Durable Queue :" + v);
        };
    }


    /*Exclusive durable queue*/

    @Bean
    public Consumer<String> exclusiveDurableQueue() {
        return v -> {
            System.out.println("Received at Durable Queue:" + v);
        };
    }

    /*consumer group non exclusive queue*/
    @Bean
    public Consumer<String> consumerGroup() {
        return v -> {
            System.out.println("Received at Consumer Group : " + v);
        };
    }

    /* consumer group nonexclusive queue with concurrency*/
    @Bean
    public Consumer<String> ConcurrentConsumerGroup() {
        return v -> {
            logger.info("Received at consumer group concurrency:{}",v);
        };
    }

}

