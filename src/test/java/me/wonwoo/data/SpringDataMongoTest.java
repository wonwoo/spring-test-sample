package me.wonwoo.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 4. 18..
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class SpringDataMongoTest {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Test
  public void findByFirstNameTest() {
    Account account = accountRepository.save(new Account("wonwoo", "lee"));
    Account at = accountRepository.findByFirstName("wonwoo");
    assertThat(account.getFirstName()).isEqualTo(at.getFirstName());
    assertThat(account.getLastName()).isEqualTo(at.getLastName());
  }

  @Test
  public void mongoTemplateTest() {
    mongoTemplate.save(new Account("kevin", "lee"));
    List<Account> accounts = mongoTemplate.findAll(Account.class);
    assertThat(accounts.size()).isEqualTo(1);
    assertThat(accounts.get(0).getFirstName()).isEqualTo("kevin");
    assertThat(accounts.get(0).getLastName()).isEqualTo("lee");
  }
}
