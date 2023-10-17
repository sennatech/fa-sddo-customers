package br.com.sennatech.sddo.customers.service.customer;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.domain.entity.Customer;
import br.com.sennatech.sddo.customers.exception.CustomerValidationException;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ValidateCustomer {

  @Autowired
  private CustomerRepository customerRepository;

  public void run(String hash) {
    String decodedHash = new String(Base64.getDecoder().decode(hash)); // Decodificando o hash base64 para uma string
    if (!decodedHash.contains(":")) {
      throw new CustomerValidationException("Wrong format");
    } // Verificando se a String decodificada contém o ':' (padrão utilizado email:senha)
    String[] decodedHashSplitted = decodedHash.split(":"); // Splitando String entre o ':' para um array de String
    String email = decodedHashSplitted[0]; // Guardando o index 0 do array (no caso o email)
    String password = decodedHashSplitted[1]; // Guardando o index 1 do array (no caso a senha)
    Customer user = customerRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
    if (!user.getPassword().equals(password)) // Verificando se a senha é igual a registrada do DB
      throw new CustomerValidationException("Wrong password");
  }
}