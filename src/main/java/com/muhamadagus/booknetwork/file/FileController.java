package com.muhamadagus.booknetwork.file;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Tag(name = "File")
public class FileController {

    private final FileStorageService fileStorageService;

    @GetMapping(value = "/get-images/{book-id}/{fileName}",
    produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImages(
            @PathVariable("book-id") Integer bookId,
            @PathVariable("fileName") String bookCover,
            Authentication authentication
    ) throws IOException {
        return Objects.requireNonNull(fileStorageService.readFileFromLocation(bookId,bookCover,authentication));
    }
}
