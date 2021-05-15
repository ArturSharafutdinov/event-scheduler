package ru.ivanov.evgeny.eventscheduler.persistence.common;

import java.io.Serializable;

public interface Identified extends Serializable {
    Serializable getId();
}
