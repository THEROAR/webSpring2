package com.example;

import org.springframework.stereotype.Component;

/**
 * 增加代码注释
 */
@Component
public class User {

    private String name;

    private Message message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
