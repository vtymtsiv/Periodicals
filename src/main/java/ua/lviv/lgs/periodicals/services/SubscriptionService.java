package ua.lviv.lgs.periodicals.services;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.periodicals.entities.Periodical;
import ua.lviv.lgs.periodicals.entities.Subscription;
import ua.lviv.lgs.periodicals.entities.User;
import ua.lviv.lgs.periodicals.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;

  private final EntityManager entityManager;

  @Autowired
  public SubscriptionService(SubscriptionRepository subscriptionRepository, EntityManager entityManager) {
    this.subscriptionRepository = subscriptionRepository;
    this.entityManager = entityManager;
  }

  public void subscribe(int periodicalId, int userId) {
    //Todo Handle if user subscribed
    Periodical periodical = entityManager.getReference(Periodical.class, periodicalId);
    User user = entityManager.getReference(User.class, userId);

    Subscription subscription = new Subscription();

    subscription.setPeriodical(periodical);
    subscription.setUser(user);
    subscription.setSubscribedAt(LocalDateTime.now());

    subscriptionRepository.save(subscription);

  }
}
