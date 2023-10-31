package br.com.sennatech.sddo.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sennatech.sddo.customers.domain.entity.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> { 
}
