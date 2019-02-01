package com.example.bookpub;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.bookpub.entity.Book;
import com.example.bookpub.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookPubApplicationTests {

  public static final String SERVER_ENDPOINT = "http://localhost:";
  @Autowired
  private WebApplicationContext context;
  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private BookRepository bookRepository;

  @LocalServerPort
  private int port;

  private MockMvc mockMvc;

  @Before
  public void setupMockMvc() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void contextLoads() {
    assertEquals(1, bookRepository.count());
  }

  @Test
  public void webAppBookIsbnApi() {
    Book book = restTemplate
        .getForObject(SERVER_ENDPOINT + this.port + "/books/978-1-78528-415-1", Book.class);

    assertNotNull(book);
    assertEquals("Packt", book.getPublisher().getName());
  }

  @Test
  public void webAppPublisherApi() throws Exception {
    this.mockMvc.perform(get("/publishers/1")).andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(
                MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(MockMvcResultMatchers.content()
            .string(containsString("Packt")))
        .andExpect(jsonPath("$.name").value("Packt"));
  }
}

