package com.tes.book.service;

import com.tes.book.dto.BookRequest;
import com.tes.book.dto.Response;

public interface BookService {
    Response addBook(BookRequest request);

    Response getBooks();

    Response getBooksDeleted();

    Response getBookById(String uuid);

    Response updateBookById(String uuid, BookRequest request);

    Response deletedBoookById(String uuid);
}
