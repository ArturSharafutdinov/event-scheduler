package ru.ivanov.evgeny.eventscheduler.persistence.dto;


import ru.ivanov.evgeny.eventscheduler.persistence.enums.Gender;

public class AccountDto extends LongIdEntityDto {

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private Integer age;

    private Gender gender;

    private String city;

    private FileInfoDto fileInfo;

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

    public FileInfoDto getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfoDto fileInfo) {
        this.fileInfo = fileInfo;
    }
}
