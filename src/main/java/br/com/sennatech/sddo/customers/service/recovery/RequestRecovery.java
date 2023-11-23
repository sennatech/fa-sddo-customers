package br.com.sennatech.sddo.customers.service.recovery;

import java.io.IOException;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.config.Config;
import br.com.sennatech.sddo.customers.domain.dto.RecoveryRequestDTO;
import br.com.sennatech.sddo.customers.domain.entity.RecoveryRequest;
import br.com.sennatech.sddo.customers.function.CreateRecoveryRequestFromDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import br.com.sennatech.sddo.customers.repository.PasswordRecoveryRepository;
import br.com.sennatech.sddo.customers.service.smtp.EmailService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestRecovery {

  private final CustomerRepository customerRepository;
  private final PasswordRecoveryRepository passwordRecoveryRepository;
  private final EmailService emailService;
  private final CreateRecoveryRequestFromDTO createRecoveryRequestFromDTO;

  @Transactional
  public void run(RecoveryRequestDTO request) throws IOException, MessagingException {
    String email = request.getEmail();
    if (!customerRepository.existsByEmail(email)) throw new EntityNotFoundException("User not found"); //checando se o email existe no repositorio
    passwordRecoveryRepository.deleteAllByEmail(email); //excluindo as requisções prévias relacionadas a este email (transformando o hash em inutilizável quando for validar)
    RecoveryRequest recoveryRequest = createRecoveryRequestFromDTO.apply(request);
    passwordRecoveryRepository.save(recoveryRequest);
    emailService.send("Password recovery - Recuperação de senha", Config.EMAIL_FROM, email, recoveryRequest.getHash()); //Enviando email com html contendo link com button com hash incluso
  }
}
