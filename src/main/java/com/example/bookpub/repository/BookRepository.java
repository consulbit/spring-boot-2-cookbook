package com.example.bookpub.repository;

import com.example.bookpub.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

  public Book findBookByIsbn(final String isbn);
}
