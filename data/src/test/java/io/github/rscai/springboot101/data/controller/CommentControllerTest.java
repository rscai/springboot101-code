package io.github.rscai.springboot101.data.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.rscai.springboot101.data.model.Comment;
import io.github.rscai.springboot101.data.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:spring-data-test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.datasource.username=sa",
    "spring.datasource.password=sa",
    "spring.datasource.driverClassName=org.h2.Driver"
})
public class CommentControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private WebApplicationContext webContext;

  private MockMvc mvc;
  private ObjectMapper mapper;

  private Long postId;

  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    JdbcTestUtils.deleteFromTables(jdbcTemplate, "COMMENT", "POST");

    postId = initPost();
  }

  private Long initPost() throws Exception {
    Post input = new Post();
    input.setContent("A post");

    String postResponseJson = mvc.perform(
        post("/api/post").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(input))).andExpect(status().isCreated()).andReturn()
        .getResponse().getContentAsString();
    return mapper.readValue(postResponseJson, Post.class).getId();
  }

  @Test
  public void testCreateAndGet() throws Exception {
    Comment input = new Comment();
    input.setContent("comment's content");

    mvc.perform(post("/api/post/{postId}/comment", postId).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(input)))
        .andExpect(status().isCreated()).andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.content", is("comment's content")))
        .andExpect(jsonPath("$.createdAt", notNullValue()))
        .andExpect(jsonPath("$.post.id", is(postId.intValue())));

    mvc.perform(get("/api/post/{postId}/comment", postId).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].content", is("comment's content")));
  }
}
