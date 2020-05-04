package ua.lviv.lgs.periodicals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.periodicals.entities.Periodical;

@Repository
public interface PeriodicalRepository extends JpaRepository<Periodical, Integer> {

  List<Periodical> findByActiveTrue();
}
