package ru.ivanov.evgeny.eventscheduler.persistence.dto;

public class NotificationDto extends LongIdEntityDto {
    private String type;
    private Object payload;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
