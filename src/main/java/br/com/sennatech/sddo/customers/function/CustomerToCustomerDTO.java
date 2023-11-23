package br.com.sennatech.sddo.customers.function;

import java.util.function.BiFunction;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;
import br.com.sennatech.sddo.customers.domain.entity.CustomerAddress;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerToCustomerDTO implements BiFunction<Customer, CustomerAddress, CustomerDTO> {

    private final AddressToAddressDTO addressToAddressDTO;

    @Override
    public CustomerDTO apply(Customer customer, CustomerAddress customerAddress){
        return CustomerDTO.builder()
        .address(addressToAddressDTO.apply(customerAddress))
        .areaCode(customer.getAreaCode())
        .birthdate(customer.getBirthdate())
        .documentNumber(customer.getDocumentNumber())
        .email(customer.getEmail())
        .gender(customer.getGender())
        .login(customer.getLogin())
        .name(customer.getName())
        .password(customer.getPassword())
        .phone(customer.getPhone())
        .build();
    }
}