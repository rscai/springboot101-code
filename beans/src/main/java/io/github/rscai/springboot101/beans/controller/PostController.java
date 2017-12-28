package io.github.rscai.springboot101.beans.controller;

import io.github.rscai.springboot101.beans.dao.PostDao;
import io.github.rscai.springboot101.beans.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
public class PostController {

  
  private PostDao postDao;

  @Autowired
  public PostController(PostDao postDao) {
    this.postDao = postDao;
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public Post getOne(@PathVariable("id") final long id) {
    return postDao.findOne(id);
  }
}
