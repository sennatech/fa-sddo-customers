package br.com.sennatech.sddo.customers.domain.dto;

import lombok.Data;

@Data
public class PasswordRequestDTO {
    String documentNumber;
    String oldPassword;
    String newPassword;
}
