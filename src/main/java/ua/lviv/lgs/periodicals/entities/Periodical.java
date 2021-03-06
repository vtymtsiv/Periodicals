package ua.lviv.lgs.periodicals.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "periodicals")
public class Periodical {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String description;
  @Column(name = "created_at")
  private LocalDateTime createAt;
  private boolean active;
  @OneToMany(mappedBy = "periodical")
  private List<PeriodicalPhoto> photos;

  public Periodical() {
  }

  public Periodical(String name, String description, boolean active) {
    this.name = name;
    this.description = description;
    this.active = active;
  }


  public List<PeriodicalPhoto> getPhotos() {
    return photos;
  }

  public void setPhotos(List<PeriodicalPhoto> photos) {
    this.photos = photos;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
