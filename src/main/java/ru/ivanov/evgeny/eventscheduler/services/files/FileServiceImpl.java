package ru.ivanov.evgeny.eventscheduler.services.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.FileRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.FileInfo;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.FileInfoDto;
import ru.ivanov.evgeny.eventscheduler.services.mappers.FileInfoMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.upload-dir}")
    private String dirPath;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    @Transactional
    public FileInfoDto uploadFile(Account account, MultipartFile file) throws IOException {
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

        return fileInfoMapper.mapToDto(fileInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Resource> load(Account account, UUID fileInfoId, HttpServletRequest request) {
        Optional<FileInfo> byId = fileRepository.findById(fileInfoId);
        Resource resource;
        if (byId.isPresent()) {
            FileInfo fileInfo = byId.get();
            resource = loadFileAsResource(fileInfo.getPath());
        } else throw new IllegalArgumentException("file info by id");

        // Try to determine file's content type
        String contentType;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            return ResponseEntity.noContent().build();
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private Resource loadFileAsResource(String filePath) {
        try {
            // Example
//            Path path =  Paths.get("C:\\Users\\admin\\Pictures\\files\\11\\11\\0\\35154.jpg");

            Path path = Paths.get(filePath);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new IllegalArgumentException("File not found ");
            }
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException("File not found ", ex);
        }
    }
}
