package me.wonwoo.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by wonwoolee on 2017. 4. 19..
 */
@RunWith(SpringRunner.class)
@RestClientTest(UserService.class)
public class UserServiceTests {

  @Autowired
  private MockRestServiceServer server;

  @Autowired
  private UserService userService;

  @Test
  public void mockRestServiceTest() throws Exception{
    this.server.expect(
        requestTo("/users/wonwoo"))
        .andRespond(withSuccess(
            new ClassPathResource("user.json", getClass()),
            MediaType.APPLICATION_JSON));
    User user = this.userService.findByUser("wonwoo");
    assertThat(user.getName()).isEqualTo("wonwoo");
    assertThat(user.getEmail()).isEqualTo("wonwoo@test.com");
    this.server.verify();
  }
}