package com.example.bookpub;

import com.example.bookpub.entity.Author;
import com.example.bookpub.entity.Book;
import com.example.bookpub.entity.Publisher;
import com.example.bookpub.repository.AuthorRepository;
import com.example.bookpub.repository.BookRepository;
import com.example.bookpub.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class StartupRunner implements CommandLineRunner {

  protected final Logger LOG = LoggerFactory.getLogger(StartupRunner.class);

  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private AuthorRepository authorRepository;
  @Autowired
  private PublisherRepository publisherRepository;

  @Override
  public void run(String... args) throws Exception {
    Author author = new Author("Piero", "Cascio");
    author = authorRepository.save(author);

    Publisher publisher = new Publisher("Packt");
    publisher = publisherRepository.save(publisher);

    Book book = new Book("978-1-78528-415-1",
        "Spring Boot Recipes", author, publisher);

    bookRepository.save(book);
  }
}
