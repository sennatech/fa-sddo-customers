package br.com.sennatech.sddo.customers.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequestDTO {
    String oldPassword;
    String newPassword;
}
