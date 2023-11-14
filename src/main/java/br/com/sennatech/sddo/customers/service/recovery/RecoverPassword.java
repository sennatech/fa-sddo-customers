package br.com.sennatech.sddo.customers.service.recovery;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.repository.PasswordRecoveryRepository;
import br.com.sennatech.sddo.customers.domain.entity.Customer;
import br.com.sennatech.sddo.customers.domain.entity.RecoveryRequest;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecoverPassword {

  private final CustomerRepository customersRepository;
  private final PasswordRecoveryRepository passwordRecoveryRepository;

  @Transactional
  public void run(String hash, String newPassword) {
    RecoveryRequest request =  passwordRecoveryRepository.findById(hash).orElseThrow(() -> new EntityNotFoundException("Password recovery request not found"));
    Customer customer = customersRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException("User not found"));
    customer.setPassword(newPassword);
    customersRepository.save(customer);
    passwordRecoveryRepository.deleteById(hash); //após sucesso excluir a requisição de recuperação relacionada a execução atual
  }
}
