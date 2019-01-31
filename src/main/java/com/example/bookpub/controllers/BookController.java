package com.example.bookpub.controllers;

import com.example.bookpub.editors.IsbnEditor;
import com.example.bookpub.entity.Book;
import com.example.bookpub.entity.Reviewer;
import com.example.bookpub.model.Isbn;
import com.example.bookpub.repository.BookRepository;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

@RestController
@RequestMapping("/books")
public class BookController {

  private static final Log log = LogFactory.getLog(HandlerMethod.class.getClass());
  @Autowired
  private final BookRepository bookRepository;

  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping()
  public Iterable<Book> getBooks() {
    return bookRepository.findAll();
  }

  @GetMapping("/{isbn}")
  public Book getBook(@PathVariable Isbn isbn) {
    return bookRepository.findBookByIsbn(isbn.toString());
  }

  @GetMapping("/{isbn}/reviewers")
  public List<Reviewer> getReviewers(@PathVariable("isbn") final Book book) {
    return book.getReviewers();
  }


  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Isbn.class, new IsbnEditor());
  }
}
