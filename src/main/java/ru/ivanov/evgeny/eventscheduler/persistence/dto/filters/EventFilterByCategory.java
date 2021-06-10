package ru.ivanov.evgeny.eventscheduler.persistence.dto.filters;

import java.util.List;

public class EventFilterByCategory {

    private List<Long> categoryIdList;

    public List<Long> getCategoryIdList() {
        return categoryIdList;
    }

    public void setCategoryIdList(List<Long> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }

    public EventFilterByCategory(List<Long> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }
}
