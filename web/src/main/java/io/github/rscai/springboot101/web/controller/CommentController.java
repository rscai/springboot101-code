package io.github.rscai.springboot101.web.controller;

import io.github.rscai.springboot101.web.model.Comment;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/post/{postId}/comment")
public class CommentController {

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Comment create(@PathVariable("postId") final long postId,
      @RequestBody final Comment comment) {
    comment.setId(1234);
    comment.setCreatedAt(new Date());
    comment.setPostId(postId);
    return comment;
  }

  @PutMapping("{commentId}")
  public Comment update(@PathVariable("postId") final long postId,
      @PathVariable("commentId") final long commentId, @RequestBody Comment comment) {
    comment.setId(commentId);
    comment.setPostId(postId);
    return comment;
  }

  @DeleteMapping("{commentId}")
  public void delete(@PathVariable("commentId") final long commentId) {
    return;
  }

  @GetMapping("{commentId}")
  public Comment getOne(@PathVariable("postId") final long postId,
      @PathVariable("commentId") final long commentId) {
    Comment comment = new Comment();
    comment.setId(1234);
    comment.setContent("comment content");
    comment.setCreatedAt(new Date());
    comment.setPostId(postId);
    return comment;
  }

  @GetMapping
  public List<Comment> getAll(@PathVariable("postId") final long postId) {
    Comment comment1 = new Comment();
    comment1.setId(1);
    comment1.setContent("comment 1");
    comment1.setCreatedAt(new Date());
    comment1.setPostId(postId);

    Comment comment2 = new Comment();
    comment2.setId(2);
    comment2.setContent("comment 2");
    comment2.setCreatedAt(new Date());
    comment2.setPostId(postId);

    return Arrays.asList(comment1, comment2);
  }
}
