package io.github.rscai.springboot101.data.controller;

import io.github.rscai.springboot101.data.model.Post;
import io.github.rscai.springboot101.data.repository.PostRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("/api/post")
public class PostController {

  @Autowired
  private PostRepository postRepository;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Post created(@RequestBody Post post) {
    return postRepository.save(post);
  }

  @GetMapping(value = "{id}")
  public Post getOne(@PathVariable("id") Long id) throws EntityNotFoundException {
    Post existedOne = postRepository.findOne(id);
    if (existedOne == null) {
      throw new EntityNotFoundException(String.format("Post#%d not found", id));
    } else {
      return existedOne;
    }
  }

  @GetMapping
  public List<Post> getAll() {
    return postRepository.findAll();
  }
}
