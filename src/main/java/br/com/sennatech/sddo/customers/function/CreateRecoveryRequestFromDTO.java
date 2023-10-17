package br.com.sennatech.sddo.customers.function;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.function.Function;
import com.google.common.hash.Hashing;

import br.com.sennatech.sddo.customers.domain.dto.RecoveryRequestDTO;
import br.com.sennatech.sddo.customers.domain.entity.RecoveryRequest;

import org.springframework.stereotype.Component;

@Component
public class CreateRecoveryRequestFromDTO implements Function<RecoveryRequestDTO, RecoveryRequest> {

  private static Random random = new Random(); //Criando random para utilizar no método de gerar SHA256

  @Override
  public RecoveryRequest apply(RecoveryRequestDTO recoveryRequestDTO) {
    String email = recoveryRequestDTO.getEmail();
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Criando um formatador de datetime
    String date = LocalDateTime.now().format(formatador); // Formatando LocalDateTime atual usando o formatador e salvando em String
    String sha256 = randomSHA256Generator(email, date); // Utilizando método criado para gerar a String SHA256 usando o timestamp e o email
    return new RecoveryRequest(sha256, email, LocalDateTime.now()); // Retornando entidade criada
  }

  private static String randomSHA256Generator(String email, String date) {
    StringBuilder string = new StringBuilder(); // Instanciando StringBuilder para obter uma String mutável
    for (int i = 0; i < email.length(); i++) { // Laço for para cada char contido na String que contém o Email
      string.append(email.charAt(random.nextInt(email.length()))); // Acrescentando um caracter após gerar um inteiro aleatorio dentro do intervalo do largura da string e selecionar o especifico do index
    }
    string.append(date); // Acrescentando o timestamp na string
    return Hashing.sha256().hashString(string, StandardCharsets.UTF_8).toString(); // Codificando para SHA256 e retornando como string
  }
}
