package me.wonwoo.data;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by wonwoolee on 2017. 4. 18..
 */
public interface AccountRepository extends MongoRepository<Account, String> {

  Account findByFirstName(String fn);

}
