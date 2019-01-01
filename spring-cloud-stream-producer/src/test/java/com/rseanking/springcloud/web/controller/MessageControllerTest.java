package com.rseanking.springcloud.web.controller;

import static java.time.LocalTime.now;
import static java.time.temporal.ChronoUnit.SECONDS;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rseanking.springcloud.SimpleMessage;
import com.rseanking.springcloud.source.SimpleMessageSource;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @MockBean
    private SimpleMessageSource source;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldHandleMessage() throws Exception {
        // Given
        final var expectedMessage = "test-message";

        // When
        this.mockMvc.perform(post("/message").content(expectedMessage)).andExpect(status().isOk());

        // Then
        final var simpleMessageCaptor = ArgumentCaptor.forClass(SimpleMessage.class);
        verify(source).publishSimpleMessage(simpleMessageCaptor.capture());

        assertThat(simpleMessageCaptor.getValue().getMessage()).isEqualTo(expectedMessage);
        assertThat(simpleMessageCaptor.getValue().getTime()).isCloseTo(now(), within(5, SECONDS));
    }
}