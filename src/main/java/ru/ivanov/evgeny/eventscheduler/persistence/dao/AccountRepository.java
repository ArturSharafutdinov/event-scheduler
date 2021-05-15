package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);

    Account findByIdEquals(Long Id);

    Long countUserByEmail(String email);
}

