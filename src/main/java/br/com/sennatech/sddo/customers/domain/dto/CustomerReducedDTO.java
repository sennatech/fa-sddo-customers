package br.com.sennatech.sddo.customers.domain.dto;

import java.time.LocalDate;

import br.com.sennatech.sddo.customers.domain.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReducedDTO {
    private String name;
    
    private LocalDate birthdate;
    private Gender gender;
    private String login;
    private String password;
    private String email;
    private String areaCode;
    private String phone;
    private CustomerAddressDTO address;
}

