package ua.lviv.lgs.periodicals.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "periodicalPhotos")
public class PeriodicalPhoto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Lob
  private String photo;

  @ManyToOne
  @JoinColumn(name = "periodical_id")
  private Periodical periodical;

  public PeriodicalPhoto() {
  }

  public PeriodicalPhoto(String photo, Periodical periodical) {
    this.photo = photo;
    this.periodical = periodical;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public Periodical getPeriodical() {
    return periodical;
  }

  public void setPeriodical(Periodical periodical) {
    this.periodical = periodical;
  }
}
