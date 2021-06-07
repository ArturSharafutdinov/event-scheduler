package ru.ivanov.evgeny.eventscheduler.persistence.dto;

public class AccountMinimalDto extends LongIdEntityDto {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
