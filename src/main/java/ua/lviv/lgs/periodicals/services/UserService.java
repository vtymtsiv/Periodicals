package ua.lviv.lgs.periodicals.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.periodicals.dtos.UserRegistrationRequest;
import ua.lviv.lgs.periodicals.entities.User;
import ua.lviv.lgs.periodicals.entities.UserRole;
import ua.lviv.lgs.periodicals.repositories.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final EmailSendingService emailSendingService;

  @Autowired
  public UserService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder,
                     EmailSendingService emailSendingService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.emailSendingService = emailSendingService;
  }

  public void register(UserRegistrationRequest userDto) {
    User user = new User(
      userDto.getFirstName(),
      userDto.getLastName(),
      userDto.getEmail(),
      userDto.getUsername()
    );

    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setRole(UserRole.ROLE_USER);
    user.setEmailVerified(false);

    UUID uuid = UUID.randomUUID();
    user.setVerifyEmailHash(uuid.toString());
    userRepository.save(user);

    emailSendingService.sendVerificationEmail(userDto.getEmail(), uuid.toString());
  }

  public void confirmEmail(String hash) {
    userRepository.findByVerifyEmailHash(hash)
      .ifPresent(user -> userRepository.confirmEmail(user.getId()));
  }
}
