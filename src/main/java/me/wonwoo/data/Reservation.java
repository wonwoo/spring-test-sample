package me.wonwoo.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wonwoolee on 2017. 4. 18..
 */
@Entity
public class Reservation {
  @Id
  @GeneratedValue
  private Long id;

  private String reservationName;

  Reservation() {

  }
  public Reservation(String reservationName) {
    this.reservationName = reservationName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getReservationName() {
    return reservationName;
  }

  public void setReservationName(String reservationName) {
    this.reservationName = reservationName;
  }
}
