package com.rseanking.springcloud.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.rseanking.springcloud.SimpleMessage;

@Component
@EnableBinding(Source.class)
public class SimpleMessageSource {
    private MessageChannel output;

    @Autowired
    public SimpleMessageSource(MessageChannel output) {
        this.output = output;
    }

    public void publishSimpleMessage(final SimpleMessage message) {
        output.send(MessageBuilder.withPayload(message).build());
    }
}
