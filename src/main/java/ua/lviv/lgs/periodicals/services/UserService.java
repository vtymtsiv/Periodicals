package ua.lviv.lgs.periodicals.services;

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

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
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

    userRepository.save(user);
  }
}
