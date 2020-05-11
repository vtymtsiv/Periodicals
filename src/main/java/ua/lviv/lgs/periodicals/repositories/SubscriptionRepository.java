package ua.lviv.lgs.periodicals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.lgs.periodicals.entities.Subscription;

@Repository
@Transactional
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

  @Query("SELECT (count(s) > 0) FROM Subscription s " +
    "where s.periodical.id=:periodicalId " +
    "AND s.user.id=:userId")
  boolean isPresent(@Param("periodicalId") int periodicalId, @Param("userId") int userId);

  @Query("DELETE FROM Subscription s WHERE s.periodical.id = ?1 AND s.user.id = ?2")
  @Modifying
  void deleteByPeriodicalIdAndUserId(int periodicalId, int userId);
}
