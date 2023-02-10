package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
