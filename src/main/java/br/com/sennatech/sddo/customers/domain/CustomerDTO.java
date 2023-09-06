package br.com.sennatech.sddo.customers.domain;

import java.time.LocalDate;

public record CustomerDTO(String documentNumber,
                          String name,
                          LocalDate birthdate,
                          Gender gender,
                          String login,
                          String password,
                          String email,
                          String areaCode,
                          String phone,
                          Address address) {
}
