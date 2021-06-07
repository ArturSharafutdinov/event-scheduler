package ru.ivanov.evgeny.eventscheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.services.files.FileService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("files/uploadFile")
    public void uploadFile(Account account,
                                       @RequestParam("file") final MultipartFile file) throws IOException {
            fileService.uploadFile(account, file);
    }

    @GetMapping("/files/get")
    @ResponseBody
    public ResponseEntity<Resource> getFile(Account account, @RequestParam UUID id, HttpServletRequest request) {
        return fileService.load(account, id, request);
    }
}
