package br.com.sennatech.sddo.customers.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.exception.InvalidCredentialException;
import br.com.sennatech.sddo.customers.function.PasswordRequestDTOtoPasswordDTO;
import br.com.sennatech.sddo.customers.model.Customer;
import br.com.sennatech.sddo.customers.model.dto.PasswordDTO;
import br.com.sennatech.sddo.customers.model.dto.PasswordRequestDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PasswordService {

    @Autowired
    private PasswordRequestDTOtoPasswordDTO passwordRequestDTOToPasswordDTO;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public PasswordDTO update(Logger logger, String documentNumber, PasswordRequestDTO request) {
        logger.info("Looking for customer with document number: [" + documentNumber + "]");
        Customer customer = customerRepository.findById(documentNumber).orElseThrow(() -> new EntityNotFoundException("Customer not Found"));
        if (customer.getPassword().equals(request.getOldPassword())) {
            logger.info("Customer found, supplied password matches with current one, changing password");
            PasswordDTO passwordDTO = passwordRequestDTOToPasswordDTO.apply(request);
            customer.setPassword(passwordDTO.getPassword());
            logger.info("Password changed");
            return passwordDTO;
        } else throw new InvalidCredentialException("Wrong password");
    }
}
