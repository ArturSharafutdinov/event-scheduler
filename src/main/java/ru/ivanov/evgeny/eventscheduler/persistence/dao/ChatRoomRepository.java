package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.ChatRoom;

import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
    @Query("SELECT u FROM ChatRoom u WHERE u.type = ?1 and u.key = ?2")
    ChatRoom findByTypeAndKey(ChatRoom.ChatRoomType type, String key);
}
