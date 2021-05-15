package ru.ivanov.evgeny.eventscheduler.persistence.common.identity;

import java.io.Serializable;

public interface Identified extends Serializable {
    Serializable getId();
}
