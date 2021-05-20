package ru.ivanov.evgeny.eventscheduler.services.event;

import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventDto;

import java.util.List;
import java.util.UUID;

public interface EventService {

    Event submit(EventDto eventDto);

    EventDto fetchEventById(UUID id);

    List<EventDto> fetchAllEvents();

    Event getEventById(UUID id);

}
