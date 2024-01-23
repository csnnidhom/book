package com.tes.book.controller;

import com.tes.book.dto.BookRequest;
import com.tes.book.dto.Response;
import com.tes.book.service.BookService;
import com.tes.book.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity addBook(@RequestBody BookRequest bookRequest){
        Response response = bookService.addBook(bookRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity getBooks(){
        Response response = bookService.getBooks();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/deleted")
    public ResponseEntity getBooksDeleted(){
        Response response = bookService.getBooksDeleted();
        return  ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity getBookByUUID(@PathVariable String uuid){
        Response response = bookService.getBookById(uuid);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity updateBook(@PathVariable String uuid, @RequestBody BookRequest bookRequest){
        Response response = bookService.updateBookById(uuid, bookRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deletedBook(@PathVariable String uuid){
        Response response = bookService.deletedBoookById(uuid);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
