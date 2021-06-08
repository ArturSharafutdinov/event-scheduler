package ru.ivanov.evgeny.eventscheduler.persistence.domain;


import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.LongIdEntity;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.Gender;
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

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", nullable = false)
    private Gender gender;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @OneToOne
    @JoinColumn(name = "FILE_INFO_ID")
    private FileInfo imageInfo;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public FileInfo getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(FileInfo imageInfo) {
        this.imageInfo = imageInfo;
    }

    public Set<InviteRequest> getInviteRequest() {
        return inviteRequest;
    }

    public void setInviteRequest(Set<InviteRequest> inviteRequest) {
        this.inviteRequest = inviteRequest;
    }
}
