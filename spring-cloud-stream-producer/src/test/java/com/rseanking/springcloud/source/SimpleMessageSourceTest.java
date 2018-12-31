package com.rseanking.springcloud.source;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rseanking.springcloud.SimpleMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class SimpleMessageSourceTest {

    @Autowired
    private Source source;

    @Autowired
    private MessageCollector collector;

    @Autowired
    private SimpleMessageSource uut;

    @Test
    public void shouldPublishSimpleMessage() throws Exception {
        // Given
        final SimpleMessage message = new SimpleMessage();
        message.setMessage("test-message");
        message.setTime(new Date());

        final BlockingQueue<Message<?>> messages = collector.forChannel(source.output());

        // When
        uut.publishSimpleMessage(message);

        // Then
        final String expectedMessage = marshal(message);
        MatcherAssert.assertThat(messages, receivesPayloadThat(is(expectedMessage)));
    }

    private String marshal(SimpleMessage message) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }
}