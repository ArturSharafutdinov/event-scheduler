package ru.ivanov.evgeny.eventscheduler.services.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.FileRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.FileInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.upload-dir}")
    private String dirPath;

    @Autowired
    private FileRepository fileRepository;

    @Override
    @Transactional
    public FileInfo uploadFile(Account account, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setCreateTime(
                new Date()
        );
        fileInfo.setFilename(
                file.getOriginalFilename()
        );
        fileInfo.setContentType(
                file.getContentType()
        );
        String filePath = dirPath + file.getOriginalFilename();
        fileInfo.setPath(
                filePath
        );
        fileInfo.setSize(
                file.getSize()
        );
        fileRepository.save(fileInfo);

        byte[] bytes = file.getBytes();

        Path path = Paths.get(dirPath + file.getOriginalFilename());
        Files.write(path, bytes);

        return fileInfo;
    }
}
