package com.muhamadagus.booknetwork.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileUtils {
    public static byte[] readFileFromLocation(String bookCover) {
        if(StringUtils.isBlank(bookCover)) {
            return null;
        }
        try {
            // Define the base directory where images are stored
//            String baseDir = "./uploads";
            Path filePath = Paths.get(bookCover);

            if (!Files.exists(filePath)) {
                log.error("File does not exist: {}", filePath);
                return null;
            }

            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            log.error("Failed to read file from location: {}", bookCover, e);
            return null;
        }
    }

    public static String getImageUrl(String baseUrl,String bookCover,Integer id) {
        if (StringUtils.isBlank(bookCover)) {
            return null;
        }

        try {
            Path filePath = new File(bookCover).toPath();

            // Check if the file exists to avoid returning invalid URLs
            if (!Files.exists(filePath)) {
                log.error("File does not exist at location {}", filePath);
                return null;
            }

            // Return the URL to access the image
            return baseUrl + id + "/" + filePath.getFileName().toString();

        } catch (Exception e) {
            log.error("Failed to generate URL for the file at location {}", bookCover, e);
        }
        return null;
    }

    public static String getImageName(String bookCover){
        if (StringUtils.isBlank(bookCover)) {
            return null;
        }

        try {
            Path filePath = new File(bookCover).toPath();

            // Check if the file exists to avoid returning invalid URLs
            if (!Files.exists(filePath)) {
                log.error("File does not exist at location {}", filePath);
                return null;
            }

            // Return the URL to access the image
            return filePath.getFileName().toString();

        } catch (Exception e) {
            log.error("Failed to generate URL for the file at location {}", bookCover, e);
        }
        return null;
    }
}
