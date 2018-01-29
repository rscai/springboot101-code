package io.github.rscai.springboot101.data.controller;

import io.github.rscai.springboot101.data.model.Comment;
import io.github.rscai.springboot101.data.model.Post;
import io.github.rscai.springboot101.data.repository.CommentRepository;
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
@RequestMapping("/api/post/{postId}/comment")
public class CommentController {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentRepository commentRepository;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Comment create(@PathVariable("postId") Long postId, @RequestBody Comment comment)
      throws EntityNotFoundException {
    Post post = postRepository.findOne(postId);
    if (post == null) {
      throw new EntityNotFoundException(String.format("Post#%d not found", postId));
    }

    comment.setPost(post);

    return commentRepository.save(comment);
  }

  @GetMapping("{commentId}")
  public Comment getOne(@PathVariable("postId") Long postId,
      @PathVariable("commentId") Long commentId) throws EntityNotFoundException {
    Post post = postRepository.findOne(postId);
    if (post == null) {
      throw new EntityNotFoundException(String.format("Post#%d not found", postId));
    }

    Comment comment = commentRepository.findOne(commentId);
    if (comment == null) {
      throw new EntityNotFoundException(String.format("Comment#%d not found", commentId));
    }

    return comment;
  }

  @GetMapping
  public List<Comment> getAll(@PathVariable("postId") Long postId) throws EntityNotFoundException {
    Post post = postRepository.findOne(postId);
    if (post == null) {
      throw new EntityNotFoundException(String.format("Post#%d not found", postId));
    }

    return commentRepository.findByPost(post);
  }
}
