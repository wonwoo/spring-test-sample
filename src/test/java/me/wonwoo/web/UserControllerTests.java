package me.wonwoo.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by wonwoolee on 2017. 4. 19..
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;

  @Test
  public void findBynameTest() throws Exception {
    given(userService.findByUser(any()))
        .willReturn(new User("wonwoo", "wonwoo@test.com"));

    this.mvc.perform(get("/users/{name}", "wonwoo").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("wonwoo")))
        .andExpect(jsonPath("$.email", is("wonwoo@test.com")));
  }

  @Test
  public void findBynameContentJsonTest() throws Exception {
    given(userService.findByUser(any()))
        .willReturn(new User("wonwoo", "wonwoo@test.com"));
    this.mvc.perform(get("/users/{name}", "wonwoo").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"name\":\"wonwoo\",\"email\":\"wonwoo@test.com\"}"));
  }

  @Test
  public void findBynamePrintTest() throws Exception {
    given(userService.findByUser(any()))
        .willReturn(new User("wonwoo", "wonwoo@test.com"));
    this.mvc.perform(get("/users/{name}", "wonwoo").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  public void userNameNotFoundExceptionTest() throws Exception {
    given(userService.findByUser(any()))
        .willThrow(new UserNameNotFoundException("kevin"));
    this.mvc.perform(get("/users/{name}", "wonwoo").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andDo(print());
  }
}