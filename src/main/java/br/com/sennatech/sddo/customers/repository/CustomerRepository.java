package br.com.sennatech.sddo.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sennatech.sddo.customers.domain.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByDocumentNumber(String documentNumber);
    Optional<Customer> findByEmail(String email);
    boolean existsByEmail(String email);
}
