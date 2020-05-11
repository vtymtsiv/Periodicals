package ua.lviv.lgs.periodicals.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingService {
  private static final Logger LOG = LoggerFactory.getLogger(EmailSendingService.class);

  private final JavaMailSender javaMailSender;

  @Value("${appBaseDomain}")
  private String appBaseDomain;
  @Value("${verifyLink}")
  private String verifyLink;

  public EmailSendingService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendVerificationEmail(String userEmail, String hash) {
    LOG.info("Sending verification email with hash={}", hash);

    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

    simpleMailMessage.setTo(userEmail);
    simpleMailMessage.setSubject("Please verify your email");

    String text = "Click link below to confirm email and finish registration.\n";
    String link = appBaseDomain + verifyLink + hash;

    simpleMailMessage.setText(text + link);

    try {
      javaMailSender.send(simpleMailMessage);
    } catch (MailException e) {
      String message = String.format("Can't send verification email with hash=%s", hash);
      LOG.error(message, e);
    }
  }
}
