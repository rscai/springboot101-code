package io.github.rscai.springboot101.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.rscai.springboot101.web.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostTest {

  private static final String ENDPOINT = "/api/post";

  @Autowired
  protected WebApplicationContext context;

  private MockMvc mvc;
  private ObjectMapper mapper;

  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
  }

  @Test
  public void testCreate() throws Exception {
    final Post input = new Post();
    input.setContent("post content");

    mvc.perform(
        post(ENDPOINT).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(input))).andExpect(status().isCreated())
        .andExpect(jsonPath("$.content", is("post content")))
        .andExpect(jsonPath("$.createdAt", notNullValue()));
  }

  @Test
  public void testUpdate() throws Exception {
    final long postId = 1234;

    final Post post = new Post();
    post.setContent("new content");

    mvc.perform(put(ENDPOINT + "/{id}", postId).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(post))).andExpect(status().isOk()).andDo(print())
        .andExpect(jsonPath("$.content", is("new content")));
  }

  @Test
  public void testDelete() throws Exception {
    final long postId = 1234;

    mvc.perform(delete(ENDPOINT + "/{id}", postId)).andExpect(status().isOk());
  }

  @Test
  public void testGetOne() throws Exception {
    final long postId = 1234;
    mvc.perform(get(ENDPOINT + "/{id}", postId).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1234))).andExpect(jsonPath("$.createdAt", notNullValue()));
  }

  @Test
  public void testGetAll() throws Exception {
    mvc.perform(get(ENDPOINT).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));
  }

}
