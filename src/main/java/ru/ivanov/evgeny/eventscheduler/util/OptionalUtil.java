package ru.ivanov.evgeny.eventscheduler.util;

import java.util.Optional;

public class OptionalUtil {

    public static <T> T checkExistOrThrowException(Optional<T> value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Value not found");
        }
        return value.get();
    }

}
