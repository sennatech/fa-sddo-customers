package br.com.sennatech.sddo.customers.service;

import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Base64;

@Service
@AllArgsConstructor
public class ValidateCustomer {

    private final CustomerRepository repository;

    public void validate(String hash) {
        final var decodedHash = Base64.getDecoder().decode(hash);
        final var splitDecodedHash = new String(decodedHash).split(":");

        final var email = splitDecodedHash[0];
        final var password = splitDecodedHash[1];

        final var customer = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Customer not found"));
        if (!customer.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password.");
        }
    }
}
