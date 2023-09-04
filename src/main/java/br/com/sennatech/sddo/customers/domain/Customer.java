package br.com.sennatech.sddo.customers.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String documentNumber;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String login;
    private String password;
    private String email;
    private String ddd;
    private String phone;
    private Address address;
}
