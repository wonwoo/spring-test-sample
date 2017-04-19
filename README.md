
## Spring Boot 1.5 Test

### @DataTest
 - Spring data jpa Test `@DataJpaTest`
 - Spring data mongo Test `@DataMongoTest`
 - Spring jdbc Test `@JdbcTest`
 
 ```java
@RunWith(SpringRunner.class)
@DataJpaTest
public class SpringDataJpaTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  public void findByEntityManagerReservationNameTest() {
    Reservation reservation = testEntityManager.persist(new Reservation("wonwoo"));
    //someThing
  }
}
 ```

### @WebMvcTest
 - only Controller Test 
   - `@Controller`
   - `@JsonComponent`
   - `@ControllerAdvice`
   - etc web Config 
   
 ```java
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
}
```
   
### @RestClientTest
 - http client Test
    - `MockRestServiceServer`
```java
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
```

### @JsonTest
 - json Test
   - `JacksonTester`
   - `GsonTester`
   
```java
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
}
```

### @AutoConfigureRestDocs
 - rest doc test
 
 ```java
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
  
  //SomeThing Test Config
}
 ```
 
### OutputCapture
 - print output capture

```java
public class OutputCaptureTest {

  @Rule
  public OutputCapture capture = new OutputCapture();

  @Test
  public void printOutPutTest() {
    System.out.println("hello wonwoo!");
    assertThat(capture.toString(), containsString("wonwoo!"));
    assertThat(capture.toString(), equalTo("hello wonwoo!\n"));
  }
}
```
