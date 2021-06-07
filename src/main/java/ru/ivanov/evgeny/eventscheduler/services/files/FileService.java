package ru.ivanov.evgeny.eventscheduler.services.files;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.FileInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public interface FileService {

    FileInfo uploadFile(Account account, MultipartFile file) throws IOException;

    ResponseEntity<Resource> load(Account account, UUID fileInfoId, HttpServletRequest request);

}
