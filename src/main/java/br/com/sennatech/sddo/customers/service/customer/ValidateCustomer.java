package br.com.sennatech.sddo.customers.service.customer;

import java.util.Base64;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.domain.entity.Customer;
import br.com.sennatech.sddo.customers.exception.CustomerValidationException;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateCustomer {

  private final CustomerRepository customerRepository;

  public void run(String hash) {
    // Decoding the hash from Base64 to a string
    byte[] decodedBytes = Base64.getDecoder().decode(hash);
    String decodedHash = new String(decodedBytes);

    // Checking for the presence of the ':' delimiter
    if (!decodedHash.contains(":")) {
      throw new CustomerValidationException("Wrong format");
    }

    // Splitting the decoded string using the ':' delimiter
    String[] decodedHashSplitted = decodedHash.split(":", 2); // Limiting the split to 2 parts
    String email = decodedHashSplitted[0];
    String password = decodedHashSplitted[1];

    // Fetching the user from the repository by email
    Customer user = customerRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found"));

    // Comparing passwords securely using constant-time comparison
    if (!passwordsMatch(user.getPassword(), password)) {
      throw new CustomerValidationException("Wrong password");
    }
  }

  // Constant-time comparison of passwords to avoid timing attacks
  private boolean passwordsMatch(String passwordInDB, String enteredPassword) {
    if (passwordInDB.length() != enteredPassword.length()) {
      return false;
    }

    int result = 0;
    for (int i = 0; i < passwordInDB.length(); i++) {
      result |= passwordInDB.charAt(i) ^ enteredPassword.charAt(i);
    }
    return result == 0;
  }
}