package ru.ivanov.evgeny.eventscheduler.persistence.enums;

public enum EventRole {

    ADMIN("ROLE_ADMIN"),

    MODERATOR("ROLE_MODERATOR"),

    USER("ROLE_USER");

    private String caption;

    EventRole(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}
