package ru.ivanov.evgeny.eventscheduler.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.geojson.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.CategoryDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventDto;
import ru.ivanov.evgeny.eventscheduler.services.category.CategoryService;
import ru.ivanov.evgeny.eventscheduler.services.event.EventService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EventController {

    @Autowired
    private EventService eventService;


    @Autowired
    private CategoryService categoryService;

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

    @GetMapping("/categories")
    public List<CategoryDto> getCategories() {
        return categoryService.fetchAllCategories();
    }

}
