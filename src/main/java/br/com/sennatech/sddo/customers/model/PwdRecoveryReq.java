package br.com.sennatech.sddo.customers.model;
import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "password_recovery_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PwdRecoveryReq {

  @Id
  @Column(unique = true, nullable = false, updatable = false)
  private String hash;

  @Column(length = 100, nullable = false, unique = false, updatable = false)
  private String email;

  @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp timestamp;
}
