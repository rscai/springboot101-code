package io.github.rscai.springboot101.data.repository;

import io.github.rscai.springboot101.data.model.Comment;
import io.github.rscai.springboot101.data.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByPost(Post post);
}
