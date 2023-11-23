package br.com.sennatech.sddo.customers.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressDTO {
    private String street;
    private String number;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private Long zipcode;
    private String complement;
}

