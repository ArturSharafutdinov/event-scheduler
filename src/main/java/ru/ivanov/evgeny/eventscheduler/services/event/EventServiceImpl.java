package ru.ivanov.evgeny.eventscheduler.services.event;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.EventMemberRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.EventRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.EventMember;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventDto;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.EventRole;
import ru.ivanov.evgeny.eventscheduler.services.auth.AccountService;
import ru.ivanov.evgeny.eventscheduler.services.mappers.EventMapper;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EventMemberRepository eventMemberRepository;

    @Autowired
    private EventMapper eventMapper;

    @Override
    @Transactional
    public Event submit(EventDto eventDto) {
        Event event;
        if (eventDto.getId() != null) {
            event = eventRepository.findById(eventDto.getId()).orElse(null);
            if (event == null) {
                throw new IllegalStateException("event.id");
            }
        } else {
            event = eventMapper.mapToEntity(eventDto);
            event = eventRepository.saveAndFlush(event);
            addEventMember((UUID) event.getId(), eventDto.getOwnerId(), EventRole.ADMIN);
        }
        return event;
    }

    @Override
    @Transactional(readOnly = true)
    public EventDto fetchEventById(UUID id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalStateException("Event not found by id"));
        return eventMapper.mapToDto(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDto> fetchAllEvents() {
        return eventRepository.findAll().stream().map(e -> eventMapper.mapToDto(e)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Event getEventById(UUID id) {
        return eventRepository.findById(id).orElseThrow(() -> new IllegalStateException("No event with id = " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public FeatureCollection getEventsByBounds(Double[] latitude, Double[] longitude) {
        List<Event> events = eventRepository.findAllByLatitudeBetweenAndLongitudeBetween(latitude[0], latitude[1], longitude[0], longitude[1]);
        List<Feature> features = new ArrayList<>();
        for (Event event : events) {
            Point point = new Point(event.getLatitude(), event.getLongitude());
            Map<String, Object> map = new HashMap<>() {{
                put("balloonContentHeader", event.getName());
                put("balloonContentBody", event.getDescription());
                put("balloonContentFooter", getEventStartTimeString(event.getStartTime()));
                put("hintContent", event.getName());
            }};
            Feature feature = new Feature();
            feature.setGeometry(point);
            feature.setProperties(map);
            feature.setId(event.getId().toString());
            features.add(feature);
        }

        FeatureCollection featureCollection = new FeatureCollection();
        featureCollection.setFeatures(features);

        return featureCollection;
    }

    private String getEventStartTimeString(LocalDateTime eventStartTime) {
        return "Начало мероприятия:" +
                " " +
                eventStartTime.getDayOfMonth() +
                " " +
                getMonthName(eventStartTime.getMonth()) +
                " в " +
                eventStartTime.getHour() +
                ":" +
                eventStartTime.getMinute();
    }

    private String getMonthName(Month month) {
        switch (month) {
            case JANUARY -> {
                return "Января";
            }
            case FEBRUARY -> {
                return "Февраля";
            }
            case MARCH -> {
                return "Марта";
            }
            case APRIL -> {
                return "Апреля";
            }
            case MAY -> {
                return "Мая";
            }
            case JUNE -> {
                return "Июня";
            }
            case JULY -> {
                return "Июля";
            }
            case AUGUST -> {
                return "Августа";
            }
            case SEPTEMBER -> {
                return "Сентября";
            }
            case OCTOBER -> {
                return "Октября";
            }
            case NOVEMBER -> {
                return "Ноября";
            }
            case DECEMBER -> {
                return "Декабря";
            }
            default -> {
                return "";
            }
        }

    }


    @Transactional
    public void addEventMember(UUID eventId, Long accountId, EventRole eventRole) {
        EventMember eventMember = new EventMember();
        Account account = accountService.getAccountById(accountId);
        Event event = getEventById(eventId);
        eventMember.setEvent(event);
        eventMember.setAccount(account);
        eventMember.setRole(eventRole);
        eventMemberRepository.save(eventMember);
    }


}
