package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.FileInfo;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.FileInfoDto;

import java.util.UUID;

@Component
public class FileInfoMapper {
    public FileInfoDto mapToDto(FileInfo fileInfo) {
        FileInfoDto dto = new FileInfoDto();
        dto.setContentType(fileInfo.getContentType());
        dto.setFilename(fileInfo.getFilename());
        dto.setSize(fileInfo.getSize());
        dto.setId((UUID) fileInfo.getId());
        return dto;
    }
}
