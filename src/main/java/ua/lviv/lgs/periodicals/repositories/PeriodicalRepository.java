package ua.lviv.lgs.periodicals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.periodicals.entities.Periodical;

@Repository
public interface PeriodicalRepository extends JpaRepository<Periodical, Integer> {

  List<Periodical> findByActiveTrue();

  @Query("SELECT p.id FROM Periodical p " +
    "join Subscription s " +
    "on s.periodical.id = p.id " +
    "join User u " +
    "on s.user.id = u.id AND u.id = :userId")
  List<Integer> getAllIdsByUserId(@Param("userId") int userId);
}
