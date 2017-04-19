package me.wonwoo.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 4. 18..
 */
@RunWith(SpringRunner.class)
@JdbcTest
public class SpringJdbcTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void findByReservationTest() {
    jdbcTemplate.update("insert into reservation (reservation_name) values(?)", "wonwoo");

    Reservation reservation = jdbcTemplate.queryForObject("select reservation_name from reservation where reservation_name = ?",
        (resultSet, i) -> new Reservation(resultSet.getString("reservation_name")), "wonwoo");
    assertThat(reservation.getReservationName()).isEqualTo("wonwoo");
  }

}


