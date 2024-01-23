package com.tes.book.repository;

import com.tes.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query(
            value = "select * from book",
            nativeQuery = true
    )
    List<Book> getAllBook();
    @Query(
            value = "select * from book where id=?",
            nativeQuery = true
    )
    Optional<Book> getBookByUUID(String uuid);


}
