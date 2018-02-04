package me.wonwoo.json;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class UserBasicJsonTests {

  @Autowired
  private BasicJsonTester json;

  @Test
  public void userDeserializeTest() throws Exception {
    assertThat(this.json.from("user.json"))
        .extractingJsonPathStringValue("@.name").isEqualTo("kevin");

  }
}
