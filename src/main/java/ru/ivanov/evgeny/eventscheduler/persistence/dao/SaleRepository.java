package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
