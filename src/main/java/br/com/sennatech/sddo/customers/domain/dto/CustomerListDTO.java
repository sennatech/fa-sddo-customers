package br.com.sennatech.sddo.customers.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerListDTO {
    String documentNumber;
    String email;
    String name;
}

