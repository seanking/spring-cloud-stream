package com.rseanking.springcloud.web.controller;

import static java.time.LocalTime.now;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rseanking.springcloud.SimpleMessage;
import com.rseanking.springcloud.source.SimpleMessageSource;

@RestController
public class MessageController {
    private SimpleMessageSource source;

    @Autowired
    public MessageController(SimpleMessageSource source) {
        this.source = source;
    }

    @RequestMapping(value = "/message", method = POST)
    public void handleMessage(@RequestBody String message) {
        final SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setTime(now());
        simpleMessage.setMessage(message);

        source.publishSimpleMessage(simpleMessage);
    }
}
