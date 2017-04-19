package me.wonwoo.document;

import me.wonwoo.web.User;
import me.wonwoo.web.UserController;
import me.wonwoo.web.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wonwoolee on 2017. 4. 19..
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureRestDocs("target/generated-snippets")
public class UserDocumentationTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;

  @Test
  public void getUserDocuments() throws Exception {
    given(userService.findByUser(any()))
        .willReturn(new User("wonwoo", "wonwoo@test.com"));
    this.mvc.perform(get("/users/{name}", "wonwoo").accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @TestConfiguration
  static class ResultHandlerConfiguration implements RestDocsMockMvcConfigurationCustomizer {

    @Bean
    public RestDocumentationResultHandler restDocumentation() {
      return MockMvcRestDocumentation.document("{method-name}");
    }

    @Override
    public void customize(MockMvcRestDocumentationConfigurer configurer) {
//      configurer.snippets().withTemplateFormat(TemplateFormats.markdown());
      configurer.snippets().withTemplateFormat(TemplateFormats.asciidoctor());
    }
  }
}
