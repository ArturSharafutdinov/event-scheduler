package ru.ivanov.evgeny.eventscheduler.persistence.dto;

public class CategoryDto extends LongIdEntityDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
