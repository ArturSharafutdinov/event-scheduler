package ru.ivanov.evgeny.eventscheduler.services.event;

import org.geojson.FeatureCollection;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventMemberDto;

import java.util.List;
import java.util.UUID;

public interface EventService {

    Event submit(EventDto eventDto);

    EventDto fetchEventById(UUID id);

    List<EventDto> fetchAllEvents();

    Event getEventById(UUID id);

    FeatureCollection getEventsByBounds(Double[] latitude, Double[] longitude);

    EventMemberDto checkAccountAsEventMember(Account account, UUID eventId);

}
