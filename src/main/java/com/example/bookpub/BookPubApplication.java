package com.example.bookpub;

import com.example.bookpub.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class BookPubApplication {

  private static final Logger LOG = LoggerFactory.getLogger(BookPubApplication.class);
  @Autowired
  private BookRepository bookRepository;

  public static void main(String[] args) {
    SpringApplication.run(BookPubApplication.class, args);
  }

  @Scheduled(initialDelay = 1000, fixedDelay = 10000)
  public void run() {
    LOG.info("Numbers of book: {}", this.bookRepository.count());
  }
  @Bean
  public StartupRunner scheduleRunner() {
    return new StartupRunner();
  }
}

