package br.com.sennatech.sddo.customers.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    String street;
    int number;
    String neighbourhood;
    String city;
    String state;
    String country;
    long zipCode;
    String complement;
}

