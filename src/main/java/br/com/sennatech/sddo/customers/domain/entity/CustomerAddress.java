package br.com.sennatech.sddo.customers.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_addresses")
public class CustomerAddress {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, length = 100)
    private String street;
    @Column(nullable = false, length = 4)
    private String number;
    @Column(nullable = false, length = 100)
    private String neighbourhood;
    @Column(nullable = false, length = 30)
    private String city;
    @Column(nullable = false, length = 2)
    private String state;
    @Column(nullable = false)
    private Long zipcode;
    @Column(nullable = false, length = 20)
    private String country;
}
