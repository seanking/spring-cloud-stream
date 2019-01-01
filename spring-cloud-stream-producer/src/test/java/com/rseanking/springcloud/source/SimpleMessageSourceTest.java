package com.rseanking.springcloud.source;

import java.time.LocalTime;
import java.util.concurrent.BlockingQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rseanking.springcloud.ProducerApplication;
import com.rseanking.springcloud.SimpleMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ProducerApplication.class)
public class SimpleMessageSourceTest {

    @Autowired
    private MessageChannel output;
    @Autowired
    private MessageCollector collector;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SimpleMessageSource uut;


    @Test
    public void shouldPublishSimpleMessage() throws Exception {
        // Given
        final SimpleMessage message = new SimpleMessage();
        message.setMessage("test-message");
        message.setTime(LocalTime.now());

        final BlockingQueue<Message<?>> messages = collector.forChannel(output);

        // When
        uut.publishSimpleMessage(message);

        // Then
        final String expectedMessage = convertToJSON(message);
        assertThat(messages, receivesPayloadThat(is(expectedMessage)));
    }

    private String convertToJSON(final SimpleMessage message) throws Exception {
        return mapper.writeValueAsString(message);
    }
}