package ru.ivanov.evgeny.eventscheduler.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.websocket.service.WebsocketService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {
    @Autowired
    private WebsocketService websocketService;

    @GetMapping("/test")
    public void test() {
        Event event = new Event();
        event.setName("EventName");
        websocketService.send("/chat/event/" + "123", event);
    }
}
