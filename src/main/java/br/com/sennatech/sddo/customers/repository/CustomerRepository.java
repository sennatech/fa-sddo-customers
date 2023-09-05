package br.com.sennatech.sddo.customers.repository;

import br.com.sennatech.sddo.customers.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, String> {
}
