package br.com.sennatech.sddo.customers.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    private String documentNumber;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "DATE DEFAULT '2003-01-01'")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private String gender;

    @Column(length = 50, nullable = false)
    private String login;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(length = 2, nullable = false)
    private String areaCode;

    @Column(length = 9, nullable = false)
    private String phone;

    @Embedded
    private Address address;
}
