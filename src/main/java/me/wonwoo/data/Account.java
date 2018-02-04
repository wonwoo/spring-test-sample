package me.wonwoo.data;

import org.springframework.data.annotation.Id;

/**
 * Created by wonwoolee on 2017. 4. 18..
 */
public class Account {

  @Id
  private String id;

  private String firstName;
  private String lastName;

  public Account(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}
