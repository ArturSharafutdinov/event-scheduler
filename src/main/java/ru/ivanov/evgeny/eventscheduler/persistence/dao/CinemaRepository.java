package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Cinema;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.City;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    int countAllByCity(City city);

    List<Cinema> findAllByCity(City city);

}
