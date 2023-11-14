package br.com.sennatech.sddo.customers.service.smtp;

import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.config.Config;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender mailSender;

  public void send(String subject, String from, String to, String hash) throws IOException, MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
    helper.setFrom(from);
    helper.setTo(to);
    helper.setText(getHtmlTemplateContent("email-template.html", hash), true);
    helper.setSubject(subject);
    mailSender.send(message);
  }

  private String getHtmlTemplateContent(String templateFilePath, String hash) throws IOException {
    String htmlTemplate;
    try (InputStream input = getClass().getClassLoader().getResourceAsStream(templateFilePath)) {
      if (input == null) {
        throw new FileNotFoundException("Template file not found: " + templateFilePath);
      }
      htmlTemplate = new String(input.readAllBytes(), StandardCharsets.UTF_8);
    }

    String link = Config.RECOVERY_HOSTNAME + "/" + hash;
    return htmlTemplate.replace("$link", link);
  }
}
