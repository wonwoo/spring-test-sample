package me.wonwoo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by wonwoolee on 2017. 4. 18..
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  Optional<Reservation> findByReservationName(String rn);
}
