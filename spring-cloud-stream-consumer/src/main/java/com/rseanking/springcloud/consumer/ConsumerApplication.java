package com.rseanking.springcloud.consumer;

import java.util.logging.Logger;

import static org.springframework.cloud.stream.messaging.Sink.INPUT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.rseanking.springcloud.SimpleMessage;

@SpringBootApplication
@EnableBinding(Sink.class)
public class ConsumerApplication {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }

    @StreamListener(INPUT)
    public void logSink(SimpleMessage message) {
        logger.info("Message received: " + message);
    }
}
