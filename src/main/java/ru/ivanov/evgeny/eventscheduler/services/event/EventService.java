package ru.ivanov.evgeny.eventscheduler.services.event;

import org.geojson.FeatureCollection;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventMemberDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.filters.EventFilterByCategory;

import java.util.List;
import java.util.UUID;

public interface EventService {

    Event submit(EventDto eventDto);

    EventDto fetchEventById(UUID id);

    List<EventDto> fetchAllEvents();

    Event getEventById(UUID id);

    FeatureCollection getEventsByBounds(Double[] latitude, Double[] longitude);

    FeatureCollection getEventsByBoundsWithFilter(Double[] latitude, Double[] longitude, EventFilterByCategory filter);

    EventMemberDto checkAccountAsEventMember(Account account, UUID eventId);

    List<EventMemberDto> fetchEventMembersByEvent(Account account, UUID eventId);

    List<EventDto> getEventsByUserAsCreator(Account account);

    List<EventDto> getEventsByUserAsMember(Account account);

    EventDto closeEvent(Account account, UUID eventId);

}
