package ru.ivanov.evgeny.eventscheduler.services.mappers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.AccountRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.CategoryRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Category;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class EventMapper {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public Event mapToEntity(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setMaxNumberOfParticipants(eventDto.getMaxNumberOfParticipants());
        event.setPrivateAccess(eventDto.getPrivateAccess());
        event.setDuration(eventDto.getDuration());

        //account
        Account account = accountRepository.findById(eventDto.getOwnerId()).orElseThrow(() -> new IllegalArgumentException("No such account"));
        event.setOwner(account);

        //time
        event.setStartTime(eventDto.getStartTime());
        event.setCreatedTime(LocalDateTime.now());

        //category
        Category category = categoryRepository.findById(eventDto.getCategory().getId()).orElse(null);
        event.setCategory(category);

        //coordinates
//        String coordinates = Arrays.stream(eventDto.getCoordinates())
//                .map(Object::toString)
//                .collect(Collectors.joining(" "));
//        event.setCoordinates(coordinates);

        event.setLatitude(eventDto.getCoordinates()[0]);
        event.setLongitude(eventDto.getCoordinates()[1]);


        return event;
    }

    public EventDto mapToDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId((UUID) event.getId());
        eventDto.setName(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setMaxNumberOfParticipants(event.getMaxNumberOfParticipants());
        event.setPrivateAccess(event.getPrivateAccess());
        eventDto.setOwnerId(event.getOwner().getId());
        eventDto.setDuration(event.getDuration());

        //time
        eventDto.setCreatedTime(event.getCreatedTime());
        eventDto.setStartTime(event.getStartTime());
        eventDto.setFinishTime(event.getFinishTime());

        //coordinates
//        Double[] coordinates = Arrays.stream(event.getCoordinates().split(" "))
//                .mapToDouble(Double::parseDouble)
//                .boxed()
//                .toArray(Double[]::new);
        Double[] coordinates = {
                event.getLatitude(), event.getLongitude()
        };
        eventDto.setCategory(categoryMapper.mapToDto(event.getCategory()));
        eventDto.setCoordinates(coordinates);

        return eventDto;
    }

}
