package br.com.sennatech.sddo.customers.service.recovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.repository.PasswordRecoveryRepository;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RecoverPassword {

  @Autowired
  private CustomerRepository customersRepository;

  @Autowired
  private PasswordRecoveryRepository passwordRecoveryRepository;

  @Transactional
  /*
   * Simplesmente um serviço de atualização de senha sem necessitar da senha antiga, que roda após o fluxo de recuperação for concluido com sucesso
   */
  public void run(String hash, String newPassword) {
    var request =  passwordRecoveryRepository.findById(hash).orElseThrow(() -> new EntityNotFoundException("Password recovery request not found"));
    var customer = customersRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException("User not found"));
    customer.setPassword(newPassword);
    customersRepository.save(customer);
    passwordRecoveryRepository.deleteById(hash); //após sucesso excluir a requisição de recuperação relacionada a execução atual
  }

}
