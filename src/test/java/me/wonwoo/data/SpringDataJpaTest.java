package me.wonwoo.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 4. 18..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SpringDataJpaTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private ReservationRepository reservationRepository;


  @Test
  public void findByEntityManagerReservationNameTest() {
    Reservation reservation = testEntityManager.persist(new Reservation("wonwoo"));
    Reservation rn = testEntityManager.find(Reservation.class, reservation.getId());
    assertThat(reservation.getReservationName()).isEqualTo(rn.getReservationName());
  }

  @Test
  public void findByRepositoryReservationNameTest() {
    Reservation reservation = reservationRepository.save(new Reservation("youngjin"));
    Optional<Reservation> rn = reservationRepository.findByReservationName("youngjin");
    assertThat(reservation.getReservationName()).isEqualTo(rn.get().getReservationName());
  }

  @Test
  public void findByEntityManagerAndRepositoryReservationNameTest() {
    Reservation reservation = testEntityManager.persist(new Reservation("seungwowoo"));
    Optional<Reservation> rn = reservationRepository.findByReservationName("seungwowoo");
    assertThat(reservation.getReservationName()).isEqualTo(rn.get().getReservationName());
  }


}
