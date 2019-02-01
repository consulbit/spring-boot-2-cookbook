package com.example.bookpub;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;

import com.example.bookpub.repository.PublisherRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class MockPublisherRepositoryTests {

  @MockBean
  private PublisherRepository publisherRepository;

  @Before
  public void setupPublisherRepositoryMock() {
    given(publisherRepository.count()).willReturn(5L);
  }

  @Test
  public void publishersExist() {
    Assertions.assertThat(publisherRepository.count()).isEqualTo(5L);
  }

  @After
  public void resetPublisherRepositoryMock() {
    reset(publisherRepository);
  }
}
