package ru.ivanov.evgeny.eventscheduler.websocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class WebsocketServiceImpl implements WebsocketService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Override
    public void send(String path, Object dto) {
        logger.debug("WS event: {}", path);
        messagingTemplate.convertAndSend(path, dto);
    }
}
