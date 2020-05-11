package ua.lviv.lgs.periodicals.dtos;

import java.util.List;

import ua.lviv.lgs.periodicals.entities.PeriodicalPhoto;

public class PeriodicalBaseViewDto {

  private int id;
  private String name;
  private String description;
  private List<PeriodicalPhoto> photos;
  private boolean isUserSubscribed;

  public PeriodicalBaseViewDto(int id, String name, String description, List<PeriodicalPhoto> photos) {
    this.id = id;
    this.name = name;
    this.description = description;
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

  public List<PeriodicalPhoto> getPhotos() {
    return photos;
  }

  public void setPhotos(List<PeriodicalPhoto> photos) {
    this.photos = photos;
  }

  public boolean getIsUserSubscribed() {
    return isUserSubscribed;
  }

  public void setIsUserSubscribed(boolean userSubscribed) {
    isUserSubscribed = userSubscribed;
  }
}
