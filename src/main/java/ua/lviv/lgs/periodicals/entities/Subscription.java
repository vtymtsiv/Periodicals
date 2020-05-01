package ua.lviv.lgs.periodicals.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "periodical_id")
  private Periodical periodical;

  @Column(name = "subscribed_at")
  private LocalDateTime subscribedAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Periodical getPeriodical() {
    return periodical;
  }

  public void setPeriodical(Periodical periodical) {
    this.periodical = periodical;
  }

  public LocalDateTime getSubscribedAt() {
    return subscribedAt;
  }

  public void setSubscribedAt(LocalDateTime subscribedAt) {
    this.subscribedAt = subscribedAt;
  }
}
