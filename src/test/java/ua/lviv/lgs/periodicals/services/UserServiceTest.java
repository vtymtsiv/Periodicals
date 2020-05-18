package ua.lviv.lgs.periodicals.services;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import ua.lviv.lgs.periodicals.dtos.UserRegistrationRequest;
import ua.lviv.lgs.periodicals.entities.User;
import ua.lviv.lgs.periodicals.entities.UserRole;
import ua.lviv.lgs.periodicals.repositories.UserRepository;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
  private static final String CONFIRM_HASH = "ewgf34-fg53y4-fg354h";
  private static final int USER_ID = 12;
  public static final String USER_PASSWORD = "12345";
  private static final String USER_PASSWORD_ENCODED = "pass-encoded";
  public static final String USER_EMAIL = "test@email.com";
  public static final String FIRST_NAME = "Foo";
  public static final String LAST_NAME = "Bar";
  public static final String USERNAME = "supername";

  @Mock
  private UserRepository userRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private EmailSendingService emailSendingService;

  @Captor
  private ArgumentCaptor<User> userCaptor;

  private UserService userService;

  @Before
  public void setUp() {
    userService = new UserService(userRepository, passwordEncoder, emailSendingService);
  }


  @Test
  public void itCanConfirmEmail() {
    User user = new User();
    user.setId(USER_ID);

    when(userRepository.findByVerifyEmailHash(CONFIRM_HASH))
      .thenReturn(Optional.of(user));

    userService.confirmEmail(CONFIRM_HASH);

    verify(userRepository).findByVerifyEmailHash(CONFIRM_HASH);
    verify(userRepository).confirmEmail(USER_ID);
  }

  @Test
  public void itCanConfirmEmailWhenUserNotFound() {
    when(userRepository.findByVerifyEmailHash(CONFIRM_HASH))
      .thenReturn(Optional.empty());

    userService.confirmEmail(CONFIRM_HASH);

    verify(userRepository).findByVerifyEmailHash(CONFIRM_HASH);
    verify(userRepository, times(0)).confirmEmail(anyInt());
  }

  @Test
  public void itCanRegisterUser() {
    UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
    userRegistrationRequest.setEmail(USER_EMAIL);
    userRegistrationRequest.setFirstName(FIRST_NAME);
    userRegistrationRequest.setLastName(LAST_NAME);
    userRegistrationRequest.setPassword(USER_PASSWORD);
    userRegistrationRequest.setUsername(USERNAME);

    when(passwordEncoder.encode(USER_PASSWORD))
      .thenReturn(USER_PASSWORD_ENCODED);

    userService.register(userRegistrationRequest);

    User expectedUser = new User(FIRST_NAME, LAST_NAME, USER_EMAIL, USERNAME);
    expectedUser.setRole(UserRole.ROLE_USER);
    expectedUser.setEmailVerified(false);
    expectedUser.setPassword(USER_PASSWORD_ENCODED);
    expectedUser.setVerifyEmailHash("hash");

    verify(userRepository).save(userCaptor.capture());

    assertThat(userCaptor.getValue())
      .isEqualToIgnoringGivenFields(expectedUser, "verifyEmailHash");

    verify(emailSendingService).sendVerificationEmail(eq(USER_EMAIL), anyString());
  }

}