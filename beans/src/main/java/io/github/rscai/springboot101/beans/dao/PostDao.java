package io.github.rscai.springboot101.beans.dao;

import io.github.rscai.springboot101.beans.model.Post;

public interface PostDao {

  Post findOne(final long id);
}
