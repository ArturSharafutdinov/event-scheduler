package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
