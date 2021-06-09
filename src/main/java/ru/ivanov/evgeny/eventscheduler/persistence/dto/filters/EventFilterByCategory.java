package ru.ivanov.evgeny.eventscheduler.persistence.dto.filters;

import java.util.List;

public class EventFilterByCategory {
    List<String> categoryNames;

    public EventFilterByCategory(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }
}
