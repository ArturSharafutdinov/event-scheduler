package ru.ivanov.evgeny.eventscheduler.persistence.dto;

import java.util.Date;

public class ChatMessageDto extends LongIdEntityDto {

    private AccountMinimalDto author;
    private String text;
    private Date time;

    public AccountMinimalDto getAuthor() {
        return author;
    }

    public void setAuthor(AccountMinimalDto author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
