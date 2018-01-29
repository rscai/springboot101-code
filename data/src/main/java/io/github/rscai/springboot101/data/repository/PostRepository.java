package io.github.rscai.springboot101.data.repository;

import io.github.rscai.springboot101.data.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
