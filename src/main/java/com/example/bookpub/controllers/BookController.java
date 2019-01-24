package com.example.bookpub.controllers;

import com.example.bookpub.entity.Book;
import com.example.bookpub.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired private final BookRepository bookRepository;

  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping()
  public Iterable<Book> getBooks() {
    return bookRepository.findAll();
  }

  @GetMapping("/{isbn}")
    public Book getBook(@PathVariable String isbn){
    return bookRepository.findBookByIsbn(isbn);
}
}