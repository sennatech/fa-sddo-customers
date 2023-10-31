package br.com.sennatech.sddo.customers.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers_password_recovery_requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryRequest {

  @Id
  @Column(unique = true, nullable = false, updatable = false)
  private String hash;

  @Column(length = 100, nullable = false, unique = false, updatable = false)
  private String email;

  @Column(nullable = false, updatable = false)
  private LocalDateTime timestamp;
}
