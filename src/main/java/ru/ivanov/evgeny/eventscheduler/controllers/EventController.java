package ru.ivanov.evgeny.eventscheduler.controllers;

import org.geojson.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.CategoryDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventMemberDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.filters.EventFilterByCategory;
import ru.ivanov.evgeny.eventscheduler.services.category.CategoryService;
import ru.ivanov.evgeny.eventscheduler.services.event.EventService;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EventController {

    @Autowired
    private EventService eventService;


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/event/{id}")
    @ResponseBody
    public EventDto getEvent(@PathVariable UUID id) {
        return eventService.fetchEventById(id);
    }

    @PostMapping("/event")
    public String addEvent(@RequestBody EventDto eventDto) {
        eventService.submit(eventDto);
        return "Added";
    }

    @GetMapping("/events")
    public FeatureCollection getEventsByBounds(@RequestParam List<Double> bbox) {
        Double[] latitude = {bbox.get(0), bbox.get(2)};
        Double[] longitude = {bbox.get(1), bbox.get(3)};

        return eventService.getEventsByBounds(latitude, longitude);

    }

    @GetMapping("/events/filtered")
    public FeatureCollection getFilteredEventsByBounds(@RequestParam List<Double> bbox, @RequestParam List<Long> categories) {
        Double[] latitude = {bbox.get(0), bbox.get(2)};
        Double[] longitude = {bbox.get(1), bbox.get(3)};
        EventFilterByCategory eventFilterByCategory = new EventFilterByCategory(categories);

        return eventService.getEventsByBoundsWithFilter(latitude, longitude, eventFilterByCategory);

    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories() {
        return categoryService.fetchAllCategories();
    }

    @GetMapping("/event/checkUserAsEventMember")
    public EventMemberDto checkUserAsEventMember(Account account, @RequestParam UUID eventId) {
        return eventService.checkAccountAsEventMember(account, eventId);
    }

    @GetMapping("/event/members")
    public List<EventMemberDto> fetchEventMembers(Account account, @RequestParam UUID eventId) {
        return eventService.fetchEventMembersByEvent(account, eventId);
    }

}
