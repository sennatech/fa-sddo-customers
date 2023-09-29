package br.com.sennatech.sddo.customers.service;

import br.com.sennatech.sddo.customers.domain.Customer;
import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.InvalidCredentialException;
import br.com.sennatech.sddo.customers.domain.dto.PasswordDTO;
import br.com.sennatech.sddo.customers.domain.dto.PasswordRequestDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerDTOToCustomer;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerToCustomerDTO;
import br.com.sennatech.sddo.customers.service.converters.ConvertPassordRequestToPasswordDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateCustomerPassword {

    private final ConvertPassordRequestToPasswordDTO convertPassordRequestToPasswordDTO;
    private final ConvertCustomerToCustomerDTO convertCustomerToCustomerDTO;
    private final CustomerRepository repository;
    @Transactional
    public PasswordDTO execute(String documentNumber,PasswordRequestDTO request){
        Customer customer = repository.findById(documentNumber).orElseThrow(() -> new InvalidCredentialException("Customer not Found"));
        if (customer.getPassword() == request.getOldPassword()){
        PasswordDTO password = convertPassordRequestToPasswordDTO.convert(request);
        return password;}
        else throw new InvalidCredentialException("Wrong password");
    }
}