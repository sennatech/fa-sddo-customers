package br.com.sennatech.sddo.customers.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String street;
    private String number;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String complement;
}
