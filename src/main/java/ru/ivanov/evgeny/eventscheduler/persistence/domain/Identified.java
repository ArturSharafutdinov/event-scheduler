package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import java.io.Serializable;

public interface Identified extends Serializable {
    Serializable getId();
}
