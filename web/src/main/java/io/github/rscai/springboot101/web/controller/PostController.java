package io.github.rscai.springboot101.web.controller;

import io.github.rscai.springboot101.web.model.Post;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
      MediaType.APPLICATION_JSON_VALUE})
  public Post create(@RequestBody final Post post) {
    post.setId(1234);
    post.setCreatedAt(new Date());
    return post;
  }

  @PutMapping(value = "{id}")
  public Post update(@PathVariable("id") final Long id, @RequestBody final Post post) {
    return post;
  }

  @DeleteMapping(value = "{id}")
  public void delete(@PathVariable("id") final Long id) {
    return;
  }

  @GetMapping(value = "{id}")
  public Post getOne(@PathVariable("id") final Long id) {
    Post post = new Post();
    post.setId(id);
    post.setContent("mock");
    post.setCreatedAt(new Date());
    return post;
  }

  @GetMapping
  public List<Post> getAll() {
    Post post1 = new Post();
    post1.setId(1);
    post1.setContent("content 1");
    post1.setCreatedAt(new Date());

    Post post2 = new Post();
    post2.setId(2);
    post2.setContent("content ");
    post2.setCreatedAt(new Date());

    return Arrays.asList(post1, post2);
  }


}
