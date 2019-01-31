package com.example.bookpub.formatters;

import com.example.bookpub.entity.Book;
import com.example.bookpub.repository.BookRepository;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class BookFormatter implements Formatter<Book> {

  private final BookRepository bookRepository;

  public BookFormatter(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Book parse(String bookIdentifier, Locale locale) throws ParseException {
    Book book = bookRepository.findBookByIsbn(bookIdentifier);

    return book != null ? book : bookRepository.findById(Long.valueOf(bookIdentifier)).get();
  }

  @Override
  public String print(Book book, Locale locale) {
    return book.getIsbn();
  }
}
