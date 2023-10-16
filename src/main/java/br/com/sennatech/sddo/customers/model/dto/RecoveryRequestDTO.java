package br.com.sennatech.sddo.customers.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecoveryRequestDTO {
  private String email;
}
