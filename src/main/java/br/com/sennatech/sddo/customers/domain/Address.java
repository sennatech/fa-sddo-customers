package br.com.sennatech.sddo.customers.domain;

import jakarta.persistence.Id;
import lombok.Data;

@Data

public class Address {
    private long id;
    private String street;
    private int number;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private long zipCode;
    private String complement;
}
