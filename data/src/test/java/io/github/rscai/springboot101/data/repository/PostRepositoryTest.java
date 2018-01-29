package io.github.rscai.springboot101.data.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import io.github.rscai.springboot101.data.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:spring-data-test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.datasource.username=sa",
    "spring.datasource.password=sa",
    "spring.datasource.driverClassName=org.h2.Driver"
})
public class PostRepositoryTest {

  @Autowired
  private PostRepository testObject;

  @Test
  public void testSaveAndGet() throws Exception {
    final Post inputPost = new Post();
    inputPost.setContent("new post");

    final Post savedPost = testObject.save(inputPost);

    assertThat(savedPost.getId(), notNullValue());
    assertThat(savedPost.getContent(), is("new post"));
    assertThat(savedPost.getCreatedAt(), notNullValue());

    final Post existedPost = testObject.findOne(savedPost.getId());

    assertThat(existedPost.getId(), notNullValue());
    assertThat(existedPost.getContent(), is("new post"));
    assertThat(existedPost.getCreatedAt(), notNullValue());
  }

}
