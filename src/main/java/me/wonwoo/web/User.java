package me.wonwoo.web;

/**
 * Created by wonwoolee on 2017. 4. 19..
 */
public class User {

  private String name;

  private String email;

  User() {
    
  }

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
