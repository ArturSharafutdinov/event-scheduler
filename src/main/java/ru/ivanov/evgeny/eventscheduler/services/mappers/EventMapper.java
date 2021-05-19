package ru.ivanov.evgeny.eventscheduler.services.mappers;


import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventDto;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EventMapper {

    public static Event mapToEntity(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setMaxNumberOfParticipants(eventDto.getMaxNumberOfParticipants());
        event.setPrivate(eventDto.getPrivate());
        event.setStartTime(eventDto.getStartTime());
        event.setFinishTime(eventDto.getFinishTime());

        String coordinates = Arrays.stream(eventDto.getCoordinates())
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        event.setCoordinates(coordinates);

        return event;
    }

    public static EventDto mapToDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setName(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setMaxNumberOfParticipants(event.getMaxNumberOfParticipants());
        eventDto.setPrivate(event.getPrivate());
        eventDto.setStartTime(event.getStartTime());
        eventDto.setFinishTime(event.getFinishTime());

        Double[] coordinates = Arrays.stream(event.getCoordinates().split(" "))
                .mapToDouble(Double::parseDouble)
                .boxed()
                .toArray(Double[]::new);
        eventDto.setCoordinates(coordinates);
        return eventDto;
    }

}
