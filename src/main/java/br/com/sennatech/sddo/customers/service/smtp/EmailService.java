package br.com.sennatech.sddo.customers.service.smtp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.config.Config;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  public void send(String subject, String from, String to, String hash) throws IOException, MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
    helper.setFrom(from); // Setando o
    helper.setTo(to); // Setando o destinatario
    helper.setText(htmlTemplateManipulatorToString("email-template.html", hash), true); // Setando payload do email, no caso o nosso html manipulado
    helper.setSubject(subject); // Setando o assunto do email
    mailSender.send(message); // Enviando
  }

  private String htmlTemplateManipulatorToString(String templateFilePath, String hash) throws IOException {
    InputStream input = getClass().getClassLoader().getResourceAsStream(templateFilePath); // Lendo arquivo na pasta resources pelo nome
    String html = new String(input.readAllBytes(), StandardCharsets.UTF_8); // Serializando o arquivo em string
    String link = Config.RECOVERY_HOSTNAME + "/" + hash; // Montando link com envvar + hash
    return html.replace("$link", link); // Substituindo '$link' pelo link gerado utilizando a variavel de ambiente do hostname + hash gerado e retornando
  }
}
