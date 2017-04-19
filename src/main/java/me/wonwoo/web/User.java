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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (name != null ? !name.equals(user.name) : user.name != null) return false;
    return email != null ? email.equals(user.email) : user.email == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
