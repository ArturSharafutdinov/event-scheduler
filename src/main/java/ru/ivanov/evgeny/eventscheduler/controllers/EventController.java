package ru.ivanov.evgeny.eventscheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventDto;
import ru.ivanov.evgeny.eventscheduler.services.event.EventService;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event")
    public String addEvent(@RequestBody EventDto eventDto) {
        eventService.submit(eventDto);
        return "Added";
    }

}
