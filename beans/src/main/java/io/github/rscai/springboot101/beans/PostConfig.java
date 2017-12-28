package io.github.rscai.springboot101.beans;

import io.github.rscai.springboot101.beans.dao.PostDao;
import io.github.rscai.springboot101.beans.dao.PostDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfig {

  @Bean
  public PostDao postDao() {
    return new PostDaoImpl();
  }
}
