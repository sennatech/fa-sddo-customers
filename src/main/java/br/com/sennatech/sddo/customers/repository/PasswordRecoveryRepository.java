package br.com.sennatech.sddo.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sennatech.sddo.customers.domain.entity.RecoveryRequest;

public interface PasswordRecoveryRepository extends JpaRepository<RecoveryRequest, String> {
  void deleteAllByEmail(String email);
}
