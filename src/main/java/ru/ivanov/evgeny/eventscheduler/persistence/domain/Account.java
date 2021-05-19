package ru.ivanov.evgeny.eventscheduler.persistence.domain;


import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.LongIdEntity;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
@AttributeOverride(name = "id", column = @Column(name = "ACCOUNT_ID"))
public class Account extends LongIdEntity {

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<InviteRequest> inviteRequest = new HashSet<>();

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

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<InviteRequest> getInviteRequest() {
        return inviteRequest;
    }

    public void setInviteRequest(Set<InviteRequest> inviteRequest) {
        this.inviteRequest = inviteRequest;
    }
}
