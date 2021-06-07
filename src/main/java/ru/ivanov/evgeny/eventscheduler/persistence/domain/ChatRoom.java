package ru.ivanov.evgeny.eventscheduler.persistence.domain;


import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.UUIDEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "CHAT_ROOM")
@AttributeOverride(name = "uuid", column = @Column(name = "CHAT_ROOM_ID"))
public class ChatRoom extends UUIDEntity {

    // if type.equals(Event) -> findEventById(entityId)
    // if type.equals(PRIVATE) -> ищем по сумме полей(TODO)
    @Column(name = "KEY", nullable = false)
    private String key;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private ChatRoomType type;

//    @Column(name = "CHAT_OFF")
//    private Boolean chatOff;
//
//    @Column(name = "CHAT_BLOCKED")
//    private Boolean chatBlocked;

    public String getKey() {
        return key;
    }

    public void setKey(String entityId) {
        this.key = entityId;
    }

    public ChatRoomType getType() {
        return type;
    }

    public void setType(ChatRoomType type) {
        this.type = type;
    }

//    public Boolean getChatOff() {
//        return chatOff;
//    }
//
//    public void setChatOff(Boolean chatOff) {
//        this.chatOff = chatOff;
//    }
//
//    public Boolean getChatBlocked() {
//        return chatBlocked;
//    }
//
//    public void setChatBlocked(Boolean chatBlocked) {
//        this.chatBlocked = chatBlocked;
//    }

    public enum ChatRoomType {
        PRIVATE,
        EVENT;
    }
}

