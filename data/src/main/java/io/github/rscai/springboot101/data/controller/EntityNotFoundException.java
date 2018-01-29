package io.github.rscai.springboot101.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {

  EntityNotFoundException(String msg) {
    super(msg);
  }
}
