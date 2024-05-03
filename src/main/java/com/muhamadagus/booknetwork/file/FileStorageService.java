package com.muhamadagus.booknetwork.file;

import com.muhamadagus.booknetwork.book.Book;
import com.muhamadagus.booknetwork.book.BookRepository;
import com.muhamadagus.booknetwork.user.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static java.io.File.separator;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${application.file.upload.photo-output-path}")
    private String fileUploadPath;

    private final BookRepository bookRepository;

    public String saveFile(
            @NonNull MultipartFile file,
            @NonNull Integer userId) {
        final String fileUploadSubPath = "user" + File.separator + userId;
        return uploadFile(file,fileUploadSubPath);
    }

    private String uploadFile(
            @NonNull MultipartFile file,
            @NonNull String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + File.separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if(!folderCreated) {
                log.warn("Failed to create folder {}", finalUploadPath);
                return null;
            }
        }
        final String fileExtension = getFileExtension(file.getOriginalFilename());
        String targetFilePath = finalUploadPath + File.separator + System.currentTimeMillis() + "." + fileExtension;
        Path targetFile = Paths.get(targetFilePath);
        try {
            Files.write(targetFile, file.getBytes());
            log.info("File {} uploaded to {}", file.getOriginalFilename(), targetFilePath);
        } catch (IOException e) {
            log.error("Failed to upload file {}", e.getMessage());
            return null;
        }
        return targetFilePath;
    }

    private String getFileExtension(String fileName) {
        if(fileName == null || fileName.isEmpty()){
            return null;
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if(lastDotIndex == -1){
            return null;
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();

    }

    public byte[] readFileFromLocation(
            Integer bookId,
            String bookCover,
            Authentication authentication
            ) {
        User user = (User) authentication.getPrincipal();
        Book book = bookRepository.findDisplayBooks(bookId, user.getId());
        log.info(book.getBookCover());
           return FileUtils.readFileFromLocation(book.getBookCover());
    }
}
