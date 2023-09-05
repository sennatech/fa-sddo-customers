package br.com.sennatech.sddo.customers.domain;

public record AddressDTO(String street, int number, String neighbourhood, String city, String state, String country, long zipCode, String complement) {
}
