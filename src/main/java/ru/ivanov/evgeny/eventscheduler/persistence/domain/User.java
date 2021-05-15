package ru.ivanov.evgeny.eventscheduler.persistence.domain;



import lombok.Data;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="USERS")
public class User extends LongIdEntity {

    @Column(name = "USERNAME",nullable = false)
    private String username;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "EMAIL",nullable = false)
    private String email;

    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME",nullable = false)
    private String middleName;

    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    @ElementCollection(targetClass = Role.class)
    @JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
