package com.example.bookpub.controllers;

import com.example.bookpub.entity.Publisher;
import com.example.bookpub.repository.PublisherRepository;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

  private static final Log log = LogFactory.getLog(HandlerMethod.class.getClass());

  private final PublisherRepository repository;

  public PublisherController(PublisherRepository repository) {
    this.repository = repository;
  }

  @GetMapping()
  public Iterable<Publisher> getPublishers() {
    return this.repository.findAll();
  }

  @GetMapping("/{id}")
  public Publisher getPublisherById(@PathVariable("id") Long id) {
    return this.repository.findById(id).get();
  }
}
