package ru.ivanov.evgeny.eventscheduler.persistence.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMIN("ROLE_ADMIN"),

    USER("ROLE_USER");

    private String caption;

    Role(String caption) {
        this.caption = caption;
    }

    @Override
    public String getAuthority() {
        return caption;
    }
}
