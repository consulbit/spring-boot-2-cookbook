package com.example.bookpub;

import com.example.bookpub.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class StartupRunner implements CommandLineRunner {

  protected final Logger LOG = LoggerFactory.getLogger(StartupRunner.class);

  @Override
  public void run(String... args) throws Exception {
  }
}
