package br.com.sennatech.sddo.customers.function;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.function.Function;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;
import br.com.sennatech.sddo.customers.model.PwdRecoveryReq;
import br.com.sennatech.sddo.customers.model.dto.RecoveryRequestDTO;

@Component
public class RecoveryRequestDTOtoPwdRecoveryReq implements Function<RecoveryRequestDTO, PwdRecoveryReq> {

  @Override
  public PwdRecoveryReq apply(RecoveryRequestDTO recoveryRequestDTO) {
    String email = recoveryRequestDTO.getEmail();
    Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
    String sha256 = randomSHA256Generator(email, timestamp);
    return new PwdRecoveryReq(sha256, email, timestamp);
  }

  private static String randomSHA256Generator(String email, Timestamp timestamp) {
    StringBuilder string = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < email.length(); i++) {
      string.append(email.charAt(random.nextInt(email.length())));
    }
    string.append(timestamp.toString());
    return Hashing.sha256().hashString(string, StandardCharsets.UTF_8).toString();
  }
}
