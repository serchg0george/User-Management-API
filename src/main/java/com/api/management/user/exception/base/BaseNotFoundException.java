package com.api.management.user.exception.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BaseNotFoundException  extends RuntimeException  {
    public BaseNotFoundException(Long id){super("Entity with id " + id + " not found.");}
}
