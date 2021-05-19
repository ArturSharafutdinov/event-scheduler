package ru.ivanov.evgeny.eventscheduler.services.mappers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.AccountRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    @Autowired
    private AccountRepository accountRepository;

    public Event mapToEntity(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setMaxNumberOfParticipants(eventDto.getMaxNumberOfParticipants());
        event.setPrivate(eventDto.getPrivate());

        //account
        Account account = accountRepository.findById(eventDto.getOwnerId()).orElseThrow(()->new IllegalArgumentException("No such account"));
        event.setOwner(account);

        //time
        event.setStartTime(eventDto.getStartTime());
        event.setFinishTime(eventDto.getFinishTime());
        event.setCreatedTime(LocalDateTime.now());

        String coordinates = Arrays.stream(eventDto.getCoordinates())
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        event.setCoordinates(coordinates);

        return event;
    }

    public EventDto mapToDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setName(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setMaxNumberOfParticipants(event.getMaxNumberOfParticipants());
        eventDto.setPrivate(event.getPrivate());
        eventDto.setOwnerId((Long) event.getId());

        //time
        eventDto.setCreatedTime(event.getCreatedTime());
        eventDto.setCompletedTime(event.getCompletedTime());
        eventDto.setStartTime(event.getStartTime());
        eventDto.setFinishTime(event.getFinishTime());

        //coordinates
        Double[] coordinates = Arrays.stream(event.getCoordinates().split(" "))
                .mapToDouble(Double::parseDouble)
                .boxed()
                .toArray(Double[]::new);
        eventDto.setCoordinates(coordinates);
        return eventDto;
    }

}
