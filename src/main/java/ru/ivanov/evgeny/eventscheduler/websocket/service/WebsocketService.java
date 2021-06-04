package ru.ivanov.evgeny.eventscheduler.websocket.service;

public interface WebsocketService {
    void send(String path, Object dto);
}
