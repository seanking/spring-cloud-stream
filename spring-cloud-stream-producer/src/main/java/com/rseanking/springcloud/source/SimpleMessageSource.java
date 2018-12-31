package com.rseanking.springcloud.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.rseanking.springcloud.SimpleMessage;

@Component
public class SimpleMessageSource {
    private Source source;

    @Autowired
    public SimpleMessageSource(Source source) {
        this.source = source;
    }

    public void publishSimpleMessage(final SimpleMessage message) {
        source.output().send(MessageBuilder.withPayload(message).build());
    }
}
