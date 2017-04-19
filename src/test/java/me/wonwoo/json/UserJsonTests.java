package me.wonwoo.json;

import me.wonwoo.web.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 4. 19..
 */
@RunWith(SpringRunner.class)
@JsonTest
public class UserJsonTests {

  @Autowired
  private JacksonTester<User> json;

  @Test
  public void userJsonTest() throws Exception {
    User user = new User("kevin", "kevin@test.com");
    assertThat(this.json.write(user)).isEqualToJson("user.json");
  }

  @Test
  public void userSerializeTest() throws Exception {
    User user = new User("kevin", "kevin@test.com");
    assertThat(this.json.write(user)).hasJsonPathStringValue("@.name");
    assertThat(this.json.write(user)).hasJsonPathStringValue("@.email");
    assertThat(this.json.write(user)).extractingJsonPathStringValue("@.name")
        .isEqualTo("kevin");
    assertThat(this.json.write(user)).extractingJsonPathStringValue("@.email")
        .isEqualTo("kevin@test.com");
  }
}
