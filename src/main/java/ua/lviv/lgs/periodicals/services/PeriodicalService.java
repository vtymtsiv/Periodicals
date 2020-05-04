package ua.lviv.lgs.periodicals.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.periodicals.dtos.PeriodicalCreateFormDto;
import ua.lviv.lgs.periodicals.entities.Periodical;
import ua.lviv.lgs.periodicals.repositories.PeriodicalRepository;

@Service
public class PeriodicalService {

  private final PeriodicalRepository periodicalRepository;

  @Autowired
  public PeriodicalService(PeriodicalRepository periodicalRepository) {
    this.periodicalRepository = periodicalRepository;
  }

  public void save(PeriodicalCreateFormDto periodicalCreateFormDto) {
    Periodical periodical = new Periodical(
      periodicalCreateFormDto.getName(),
      periodicalCreateFormDto.getDescription(),
      periodicalCreateFormDto.isActive()
    );

    periodical.setCreateAt(LocalDateTime.now());

    periodicalRepository.save(periodical);
  }

  public List<Periodical> findAll() {
    return periodicalRepository.findByActiveTrue();
  }
}
