package io.github.rscai.springboot101.beans.dao;

import io.github.rscai.springboot101.beans.model.Post;
import org.springframework.stereotype.Component;

public class PostDaoImpl implements PostDao {

  private String contentTemplate = "content of %d";

  public void setContentTemplate(String contentTemplate) {
    this.contentTemplate = contentTemplate;
  }

  @Override
  public Post findOne(long id) {
    Post post = new Post();
    post.setId(id);
    post.setContent(String.format(contentTemplate, id));

    return post;
  }
}
