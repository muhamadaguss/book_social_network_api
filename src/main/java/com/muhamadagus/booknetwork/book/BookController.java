package com.muhamadagus.booknetwork.book;

import com.muhamadagus.booknetwork.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController  {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(
            @RequestBody @Valid BookRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(bookService.saveBook(request,authentication ));
    }

    @GetMapping("{book-id}")
    public ResponseEntity<BookResponse> findBookById(
            @PathVariable("book-id") Integer bookId
    ){
        return ResponseEntity.ok(bookService.findBookById(bookId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size",defaultValue = "10",required = false) Integer size,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.findAllBooks(page,size,authentication));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooksByOwner(
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size",defaultValue = "10",required = false) Integer size,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.findAllBooksByOwner(page,size,authentication));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedBooksResponse>> findAllBorrowedBooks(
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size",defaultValue = "10",required = false) Integer size,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.findAllBorrowedBooks(page,size,authentication));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedBooksResponse>> findAllReturnedBooks(
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size",defaultValue = "10",required = false) Integer size,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.findAllReturnedBooks(page,size,authentication));
    }

    @PatchMapping("/shareable/{book-id}")
    public ResponseEntity<Integer> updateShareableStatus(
        @PathVariable("book-id") Integer bookId,
        Authentication authentication
    ){
        return ResponseEntity.ok(bookService.updateShareableStatus(bookId,authentication));
    }

    @PatchMapping("/archived/{book-id}")
    public ResponseEntity<Integer> updateArchivedStatus(
            @PathVariable("book-id") Integer bookId,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.updateArchivedStatus(bookId,authentication));
    }

    @PostMapping("/borrow/{book-id}")
    public ResponseEntity<Integer> borrowBook(
            @PathVariable("book-id") Integer bookId,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.borrowBook(bookId,authentication));
    }

    @PatchMapping("/borrow/return/{book-id}")
    public ResponseEntity<Integer> returnBorrowBook(
            @PathVariable("book-id") Integer bookId,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.returnBorrowBook(bookId,authentication));
    }

    @PatchMapping("/borrow/return/approve/{book-id}")
    public ResponseEntity<Integer> approveReturnBorrowBook(
            @PathVariable("book-id") Integer bookId,
            Authentication authentication
    ){
        return ResponseEntity.ok(bookService.approveReturnBorrowBook(bookId,authentication));
    }

    @PostMapping(value = "/cover/{book-id}",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadBookCoverPicture(
            @PathVariable("book-id") Integer bookId,
            @Parameter()
            @RequestPart("file") MultipartFile file,
            Authentication authentication
    ){
        bookService.uploadBookCoverPicture(bookId,file,authentication);
        return ResponseEntity.accepted().build();
    }
}
