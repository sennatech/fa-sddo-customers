package br.com.sennatech.sddo.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sennatech.sddo.customers.model.PwdRecoveryReq;

public interface PasswordRecoveryRepository extends JpaRepository<PwdRecoveryReq, String> {
  void deleteAllByEmail(String email);
}
