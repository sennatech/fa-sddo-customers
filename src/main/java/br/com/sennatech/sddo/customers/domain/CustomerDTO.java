package br.com.sennatech.sddo.customers.domain;

import java.time.LocalDate;

public record CustomerDTO(String documentNumber,
                          String name,
                          String birthdate){}

