package br.com.sennatech.sddo.customers.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecoveryDTO {
  private String newPassword;
}
