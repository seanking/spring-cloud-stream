package com.rseanking.springcloud;

import java.time.LocalTime;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SimpleMessage {
    private LocalTime time;
    private String message;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("time", time)
                .append("message", message)
                .toString();
    }
}
