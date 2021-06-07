package ru.ivanov.evgeny.eventscheduler.services.files;

import org.springframework.web.multipart.MultipartFile;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.FileInfo;

import java.io.IOException;

public interface FileService {

    FileInfo uploadFile(Account account, MultipartFile file) throws IOException;

}
