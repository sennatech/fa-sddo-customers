package br.com.sennatech.sddo.customers.repository;

import br.com.sennatech.sddo.customers.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByDocumentNumber(String documentNumber);
    Optional<Customer> findByEmail(String email);
}
