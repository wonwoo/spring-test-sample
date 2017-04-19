package me.wonwoo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wonwoolee on 2017. 4. 19..
 */
@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/{name}")
  public User findByname(String name) {
    return userService.findByUser(name);
  }
}
