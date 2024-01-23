package com.tes.book.service;

import com.tes.book.dto.BookRequest;
import com.tes.book.dto.Response;
import com.tes.book.entity.Book;
import com.tes.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    private final ValidationService validationService;
    @Override
    public Response addBook(BookRequest request) {
        validationService.validate(request);

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setYear(request.getYear());
        bookRepository.save(book);

        Response response = new Response();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Created Success");
        response.setData(book);

        return response;
    }

    @Override
    public Response getBooks() {
        List<Book> books = bookRepository.getAllBook()
                .stream()
                .filter(book -> !book.getIsDeleted())
                .collect(Collectors.toList());
        return new Response(HttpStatus.OK.value(), "Book Exists", books);
    }

    @Override
    public Response getBooksDeleted() {
        List<Book> books = bookRepository.getAllBook()
                .stream()
                .filter(Book::getIsDeleted)
                .collect(Collectors.toList());
        return new Response(HttpStatus.OK.value(), "Book Deleted", books);
    }

    @Override
    public Response getBookById(String uuid) {
        Optional<Book> book = bookRepository.getBookByUUID(uuid);
        return new Response(HttpStatus.OK.value(), "Success Get Book By Id", book);
    }

    @Override
    public Response updateBookById(String uuid, BookRequest request) {
        Book book = bookRepository.getBookByUUID(uuid)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setYear(request.getYear());
        Book save = bookRepository.save(book);

        return new Response(HttpStatus.OK.value(), "Success Update", save);
    }

    @Override
    public Response deletedBoookById(String uuid) {
        Book book = bookRepository.getBookByUUID(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        book.setIsDeleted(true);
        book = bookRepository.save(book);
        return new Response(HttpStatus.OK.value(), "Success deleted", book);
    }
}
