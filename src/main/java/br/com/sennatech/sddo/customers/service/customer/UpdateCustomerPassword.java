package br.com.sennatech.sddo.customers.service.customer;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.domain.dto.PasswordChangeDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;
import br.com.sennatech.sddo.customers.exception.InvalidCredentialException;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateCustomerPassword {

  private final CustomerRepository customerRepository;

  @Transactional
  public void run(String documentNumber, PasswordChangeDTO request) {
    Customer user = customerRepository.findById(documentNumber)
        .orElseThrow(() -> new EntityNotFoundException("Customer not Found"));
    if (user.getPassword().equals(request.getOldPassword())) {
      user.setPassword(request.getNewPassword());
      customerRepository.save(user);
    } else {
      throw new InvalidCredentialException("Wrong password");
    }
  }
}
