package ru.ivanov.evgeny.eventscheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.services.files.FileService;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public void uploadFile(Account account,
                                       @RequestParam("file") final MultipartFile file) throws IOException {
            fileService.uploadFile(account, file);
    }
}
