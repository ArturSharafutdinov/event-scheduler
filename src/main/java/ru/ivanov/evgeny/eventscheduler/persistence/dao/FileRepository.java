package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.FileInfo;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileInfo, UUID> {
}
