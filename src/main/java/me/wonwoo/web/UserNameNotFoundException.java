package me.wonwoo.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by wonwoolee on 2017. 4. 19..
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNameNotFoundException extends RuntimeException {

  private final String username;

  public UserNameNotFoundException(String username) {
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }
}
