package ru.ivanov.evgeny.eventscheduler.persistence.common.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Converter
public class EventDateConverter implements AttributeConverter<LocalDateTime, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date date) {
        if (date != null)
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return null;
    }
}
