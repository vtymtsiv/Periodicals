package ua.lviv.lgs.periodicals.services;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.periodicals.entities.Periodical;
import ua.lviv.lgs.periodicals.entities.Subscription;
import ua.lviv.lgs.periodicals.entities.User;
import ua.lviv.lgs.periodicals.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
  private static final Logger LOG = LoggerFactory.getLogger(SubscriptionService.class);

  private final SubscriptionRepository subscriptionRepository;

  private final EntityManager entityManager;

  @Autowired
  public SubscriptionService(SubscriptionRepository subscriptionRepository, EntityManager entityManager) {
    this.subscriptionRepository = subscriptionRepository;
    this.entityManager = entityManager;
  }

  public void subscribe(int periodicalId, int userId) {
    if(subscriptionRepository.isPresent(periodicalId, userId)) {
      LOG.info("Subscription already exists for user id {} to periodical id {}", userId, periodicalId);
      return;
    }

    Periodical periodical = entityManager.getReference(Periodical.class, periodicalId);
    User user = entityManager.getReference(User.class, userId);

    Subscription subscription = new Subscription();

    subscription.setPeriodical(periodical);
    subscription.setUser(user);
    subscription.setSubscribedAt(LocalDateTime.now());

    subscriptionRepository.save(subscription);
  }

  public void unsubscribe(int periodicalId, int userId) {
    subscriptionRepository.deleteByPeriodicalIdAndUserId(periodicalId, userId);
  }
}
