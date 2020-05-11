package ua.lviv.lgs.periodicals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.periodicals.entities.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

  @Query("SELECT (count(s) > 0) FROM Subscription s " +
    "where s.periodical.id=:periodicalId " +
    "AND s.user.id=:userId")
  boolean isPresent(@Param("periodicalId") int periodicalId, @Param("userId") int userId);
}
