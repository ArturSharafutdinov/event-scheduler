package ru.ivanov.evgeny.eventscheduler.persistence.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.Role;

import java.util.Collection;
import java.util.HashSet;

public class JwtAccountDto implements UserDetails {

    private final Long id;

    private final String username;

    private final String password;

    @JsonIgnore
    private final boolean isEnabled;

    @JsonIgnore
    private Role role;

    public JwtAccountDto(Long id, String username, String password, boolean isEnabled, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(){{
            add(role);
        }};
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
