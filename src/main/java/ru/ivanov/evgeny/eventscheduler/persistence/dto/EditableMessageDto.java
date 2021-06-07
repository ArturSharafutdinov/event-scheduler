package ru.ivanov.evgeny.eventscheduler.persistence.dto;

public class EditableMessageDto extends LongIdEntityDto {
    private AccountMinimalDto author;
    private String text;

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
}
