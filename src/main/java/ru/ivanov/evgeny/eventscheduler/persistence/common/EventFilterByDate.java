package ru.ivanov.evgeny.eventscheduler.persistence.common;

import ru.ivanov.evgeny.eventscheduler.persistence.enums.EventTimeLimitation;

import java.time.LocalDate;

public class EventFilterByDate {

    private EventTimeLimitation limitation;

    private LocalDate limitationDate;

    public EventTimeLimitation getLimitation() {
        return limitation;
    }

    public void setLimitation(EventTimeLimitation limitation) {
        this.limitation = limitation;
    }

    public LocalDate getLimitationDate() {
        return limitationDate;
    }

    public void setLimitationDate(LocalDate limitationDate) {
        this.limitationDate = limitationDate;
    }

    public EventFilterByDate(EventTimeLimitation limitation, LocalDate limitationDate) {
        this.limitation = limitation;
        this.limitationDate = limitationDate;
    }
}
