package br.com.sennatech.sddo.customers.service.recovery;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.config.Config;
import br.com.sennatech.sddo.customers.domain.entity.RecoveryRequest;
import br.com.sennatech.sddo.customers.exception.InvalidRecoveryHashException;
import br.com.sennatech.sddo.customers.repository.PasswordRecoveryRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ValidateRecoverHash {

  @Autowired
  PasswordRecoveryRepository passwordRecoveryRepository;

  public void run(String hash) {
    RecoveryRequest request = passwordRecoveryRepository.findById(hash).orElseThrow(() -> new EntityNotFoundException("Password recovery request not found"));
    if (LocalDateTime.now().minus(Config.RECOVER_EXPIRATION_TIME, ChronoUnit.MINUTES).isAfter(request.getTimestamp())) {
      passwordRecoveryRepository.deleteById(hash);
      throw new InvalidRecoveryHashException("Password recovery request is expired");
    } // verificando se o horario atual menos o tempo definido em minutos é depois do timestamp da requisição criada
    // TEMPO_ATUAL + INTERVALO DE EXPIRAÇÃO > TIMESTAMP DO DB = INVALIDO
  }
}
