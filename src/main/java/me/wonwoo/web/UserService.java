package me.wonwoo.web;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wonwoolee on 2017. 4. 19..
 */
@Component
public class UserService {

  private final RestTemplate restTemplate;

  public UserService (RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
  }

  public User findByUser(String name) {
    return this.restTemplate.getForObject("/users/{name}",
        User.class, name);
  }
}
