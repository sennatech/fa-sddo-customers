package br.com.sennatech.sddo.customers.domain;

import java.time.LocalDate;

public record CustomerDTO(String documentNumber,
                          String name,
                          LocalDate birthDate,
                          Gender gender,
                          String login,
                          String password,
                          String email,
                          String ddd,
                          String phone,
                          Address address) {
}
