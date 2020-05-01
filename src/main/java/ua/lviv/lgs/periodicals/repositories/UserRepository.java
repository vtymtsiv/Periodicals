package ua.lviv.lgs.periodicals.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.lgs.periodicals.entities.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);

  Optional<User> findByVerifyEmailHash(String hash);

  @Modifying
  @Query("Update User u set u.isEmailVerified=TRUE where u.id = :userId")
  void confirmEmail(@Param("userId") int userId);
}
