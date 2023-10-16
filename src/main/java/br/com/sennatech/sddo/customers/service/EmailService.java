package br.com.sennatech.sddo.customers.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.config.Config;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  public void send(String subject, String from, String to, String hash) throws IOException, MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
    helper.setFrom(from);
    helper.setTo(to);
    helper.setText(htmlTemplateManipulatorToString("email-template.html", hash), true);
    helper.setSubject(subject);
    mailSender.send(message);
  }

  private String htmlTemplateManipulatorToString(String templateFilePath, String hash) throws IOException {
    InputStream input = getClass().getClassLoader().getResourceAsStream(templateFilePath);
    String html = new String(input.readAllBytes(), StandardCharsets.UTF_8);
    String link = Config.RECOVERY_HOSTNAME + "/" + hash;
    return html = html.replace("$link", link);
  }
}
