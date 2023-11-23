package br.com.sennatech.sddo.customers.function;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import br.com.sennatech.sddo.customers.domain.dto.RecoveryRequestDTO;
import br.com.sennatech.sddo.customers.domain.entity.RecoveryRequest;
import br.com.sennatech.sddo.customers.exception.SHA256GenerationException;

import org.springframework.stereotype.Component;

@Component
public class CreateRecoveryRequestFromDTO implements Function<RecoveryRequestDTO, RecoveryRequest> {

  @Override
  public RecoveryRequest apply(RecoveryRequestDTO recoveryRequestDTO) {
    String email = recoveryRequestDTO.getEmail();
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Criando um formatador de
                                                                                       // datetime
    String date = LocalDateTime.now().format(formatador); // Formatando LocalDateTime atual usando o formatador e
                                                          // salvando em String
    String sha256 = randomSHA256Generator(email, date); // Utilizando método criado para gerar a String SHA256 usando o
                                                        // timestamp e o email
    return new RecoveryRequest(sha256, email, LocalDateTime.now()); // Retornando entidade criada
  }

  private static String randomSHA256Generator(String email, String date) {
    StringBuilder string = new StringBuilder(); // Using StringBuilder for mutable string operations
    SecureRandom random = new SecureRandom(); // Creating a secure random number generator

    for (int i = 0; i < email.length(); i++) {
      int randomIndex = random.nextInt(email.length());
      string.append(email.charAt(randomIndex)); // Appending a character based on a random index
    }

    string.append(date); // Appending the timestamp to the string

    // Using the Java MessageDigest class to generate SHA-256 hash
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(string.toString().getBytes(StandardCharsets.UTF_8));

      // Converting byte array to hexadecimal string representation
      StringBuilder hexString = new StringBuilder();
      for (byte hashByte : hashBytes) {
        String hex = Integer.toHexString(0xff & hashByte);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }

      return hexString.toString(); // Returning the SHA-256 hash as a hexadecimal string
    } catch (NoSuchAlgorithmException e) {
      // Handle exception (e.g., log or throw RuntimeException)
      throw new SHA256GenerationException("SHA-256 algorithm not found", e);
    }
  }
}
