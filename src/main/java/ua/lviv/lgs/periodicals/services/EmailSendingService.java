package ua.lviv.lgs.periodicals.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingService {

  @Autowired
  private JavaMailSender javaMailSender;

  @Value("${appBaseDomain}")
  private String appBaseDomain;
  @Value("${verifyLink}")
  private String verifyLink;

  public void sendVerificationEmail(String userEmail, String hash) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

    simpleMailMessage.setTo(userEmail);
    simpleMailMessage.setSubject("Please verify your email");

    String text = "Click link below to confirm email and finish registration.\n";
    String link = appBaseDomain + verifyLink + hash;

    simpleMailMessage.setText(text + link);

    try {
      javaMailSender.send(simpleMailMessage);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
