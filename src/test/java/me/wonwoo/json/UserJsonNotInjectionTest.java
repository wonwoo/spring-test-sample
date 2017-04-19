package me.wonwoo.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.web.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 4. 19..
 */
public class UserJsonNotInjectionTest {

  private JacksonTester<User> json;

  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
  }

  @Test
  public void testDeserialize() throws Exception {
    String user = "{\"name\":\"kevin\",\"email\":\"kevin@test.com\"}";
    assertThat(this.json.parse(user))
        .isEqualTo(new User("kevin", "kevin@test.com"));
    assertThat(this.json.parseObject(user).getName()).isEqualTo("kevin");
    assertThat(this.json.parseObject(user).getEmail()).isEqualTo("kevin@test.com");
  }
}
