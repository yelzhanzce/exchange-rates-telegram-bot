package com.yelzhan.tgbot;

public class User {
    private Long id;
    private String username;
    private String chatId;

    public User() {
    }

    public User(Long id, String username, String chatId) {
        this.id = id;
        this.username = username;
        this.chatId = chatId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
